package me.bactoria.cafe.infra.config

import io.asyncer.r2dbc.mysql.MySqlConnectionConfiguration
import io.asyncer.r2dbc.mysql.MySqlConnectionFactory
import io.asyncer.r2dbc.mysql.constant.SslMode
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.convert.converter.Converter
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.transaction.ReactiveTransactionManager
import java.time.Duration
import java.time.ZoneId

@Configuration
@EnableConfigurationProperties(R2dbcProperties::class)
@EnableR2dbcRepositories
@EnableR2dbcAuditing
class R2dbcConfig(
	private val r2dbcProperties: R2dbcProperties
) : AbstractR2dbcConfiguration() {

	@Primary
	@Bean
	override fun connectionFactory(): ConnectionFactory {
		return MySqlConnectionFactory.from(
			MySqlConnectionConfiguration.builder()
				.host(r2dbcProperties.host)
				.port(r2dbcProperties.port)
				.database(r2dbcProperties.database)
				.user(r2dbcProperties.username)
				.password(r2dbcProperties.password)
				.serverZoneId(ZoneId.of("Asia/Seoul"))
				.sslMode(SslMode.DISABLED)
				.connectTimeout(Duration.ofSeconds(5))
				.socketTimeout(Duration.ofSeconds(30))
				.build()
		)
	}



	@Bean
	fun reactiveTransactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager {
		return R2dbcTransactionManager(connectionFactory)
	}

	@Bean
	@Override
	override fun r2dbcCustomConversions(): R2dbcCustomConversions {
		val converters = listOf<Converter<*, *>>(
			// json READ/WRITE 컨버터
		)
		return R2dbcCustomConversions(storeConversions, converters)
	}
}

@ConfigurationProperties(prefix = "cafe.db.datasource")
data class R2dbcProperties(
	val host: String,
	val port: Int,
	val database: String,
	val username: String,
	val password: String,
)
