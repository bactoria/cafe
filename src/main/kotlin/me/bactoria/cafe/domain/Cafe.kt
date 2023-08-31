package me.bactoria.cafe.domain

data class Cafe(
	val id: Long,
	val name: String,
) {
	companion object {
		fun create(name: String) = Cafe(
			id = 0,
			name = name,
		)
	}
}
