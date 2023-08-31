package me.bactoria.cafe.acceptance

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.springframework.http.MediaType

object CafeSteps {
	fun 카페_개설_요청(name: String): ExtractableResponse<Response> {
		val params = mapOf(
			"name" to name
		)

		return RestAssured
			.given().log().all()
			.body(params)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.`when`().post("/cafes")
			.then().log().all().extract()
	}

	fun ExtractableResponse<Response>.카페_개설_응답_카페_아이디_추출(): Long {
		return this.body().jsonPath().getLong("id")
	}

	fun 카페_조회_요청(cafeId: Long): ExtractableResponse<Response> {
		return RestAssured
			.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.`when`().get("/cafes/$cafeId")
			.then().log().all().extract()
	}
}
