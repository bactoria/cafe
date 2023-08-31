package me.bactoria.cafe.acceptance

import kotlinx.coroutines.reactive.awaitLast
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.runBlocking
import me.bactoria.cafe.infra.config.R2dbcConfig
import org.springframework.beans.factory.InitializingBean
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.stereotype.Service
import org.springframework.transaction.reactive.TransactionalOperator
import org.springframework.transaction.reactive.executeAndAwait
import reactor.core.publisher.Mono


@Service
class DatabaseCleanup(
	private val r2dbcConfig: R2dbcConfig
) : InitializingBean {


	private var tableNames: List<String>? = null

	override fun afterPropertiesSet() {
		runBlocking {
			Mono.from(r2dbcConfig.connectionFactory().create())
				.flatMapMany { it -> it.createStatement("SHOW TABLES").execute() }
				.flatMap { it -> it.map { row, rowMetadata -> row.get(0, String::class.java)!! } }
				.collectList()
				.subscribe { tableNames = it }
		}
	}

	fun execute() {
		runBlocking {
			val factory = r2dbcConfig.connectionFactory()
			val connection = factory.create().awaitSingle()
			val rxtx = R2dbcTransactionManager(factory)
				.let { TransactionalOperator.create(it) }

			rxtx.executeAndAwait { _ -> // TODO: : Transactional 정말 동작하는지 검증 필요
				connection.createStatement("SET REFERENTIAL_INTEGRITY FALSE").execute().awaitLast()
				for (tableName in tableNames!!) {
					connection.createStatement("TRUNCATE TABLE $tableName").execute().awaitSingle()
					connection.createStatement("ALTER TABLE $tableName ALTER COLUMN ID RESTART WITH 1").execute().awaitSingle()
				}
				connection.createStatement("SET REFERENTIAL_INTEGRITY TRUE").execute().awaitSingle()
			}
		}
	}
}
