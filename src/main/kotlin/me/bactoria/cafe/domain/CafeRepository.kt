package me.bactoria.cafe.domain

interface CafeRepository {
	suspend fun save(cafe: Cafe): Cafe
	suspend fun load(cafeId: Long): Cafe?
}
