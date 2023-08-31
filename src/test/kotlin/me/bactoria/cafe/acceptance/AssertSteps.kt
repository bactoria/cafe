package me.bactoria.cafe.acceptance

import org.assertj.core.api.Assertions.assertThat

object AssertSteps {
	fun Long.isNotNull() {
		assertThat(this).isNotNull
	}

	fun String.isEqualTo(that: String) {
		assertThat(this).isEqualTo(that)
	}
}
