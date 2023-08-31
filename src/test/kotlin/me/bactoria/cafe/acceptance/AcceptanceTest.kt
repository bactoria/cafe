package me.bactoria.cafe.acceptance

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AcceptanceTest {

	@Autowired
	lateinit var databaseCleanup: DatabaseCleanup

	@BeforeEach
	fun setUp() {
		databaseCleanup.execute()
	}
}
