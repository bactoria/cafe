package me.bactoria.cafe.acceptance

import me.bactoria.cafe.acceptance.AssertSteps.isEqualTo
import me.bactoria.cafe.acceptance.AssertSteps.isNotNull
import me.bactoria.cafe.acceptance.CafeSteps.카페_개설_요청
import me.bactoria.cafe.acceptance.CafeSteps.카페_개설_응답_카페_아이디_추출
import me.bactoria.cafe.acceptance.CafeSteps.카페_조회_요청
import me.bactoria.cafe.acceptance.HttpSteps.must
import me.bactoria.cafe.acceptance.HttpSteps.본문
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

@DisplayName("카페")
class CafeAcceptanceTest : AcceptanceTest() {

	/**
	 * When 카페를 개설하면
	 *
	 * Then 카페 아이디와 카페 이름을 응답 받을 수 있다
	 */
	@DisplayName("카페 개설")
	@Test
	fun createCafe() {
		// given
		val 카페_이름 = "카페이름"

		// when
		val 카페_개설_응답 = 카페_개설_요청(카페_이름)

		// then
		카페_개설_응답 must HttpStatus.OK
		카페_개설_응답.본문().getLong("id").isNotNull()
		카페_개설_응답.본문().getString("name").isEqualTo(카페_이름)
	}

	/**
	 * Given 카페를 개설하고
	 *
	 * When 카페 아이디로 카페를 조회하면
	 *
	 * Then 카페 이름이 조회된다
	 */
	@DisplayName("카페 개설 조회")
	@Test
	fun getCafe() {
		// given
		val 카페_이름 = "카페이름"
		val 카페_아이디 = 카페_개설_요청(카페_이름).카페_개설_응답_카페_아이디_추출()

		// when
		val 카페_조회_응답 = 카페_조회_요청(카페_아이디)

		// then
		카페_조회_응답 must HttpStatus.OK
		카페_조회_응답.본문().getString("name").isEqualTo(카페_이름)
	}
}
