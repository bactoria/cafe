package me.bactoria.cafe.application

import me.bactoria.cafe.domain.Cafe
import me.bactoria.cafe.domain.CafeRepository

data class CafeDto(
	val id: Long,
	val name: String,
) {
	companion object {
		fun from(cafe: Cafe) = CafeDto(
			id = cafe.id,
			name = cafe.name,
		)
	}
}
