package me.bactoria.cafe.acceptance

import io.restassured.path.json.JsonPath
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

object HttpSteps {
	infix fun ExtractableResponse<Response>.must(expected: HttpStatus) {
		val matched = this.statusCode() == expected.value()
		assertThat(matched).isTrue
	}

	fun ExtractableResponse<Response>.본문(): JsonPath {
		return this.body().jsonPath()
	}
}
