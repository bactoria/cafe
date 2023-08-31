package me.bactoria.cafe.application

import me.bactoria.cafe.domain.Cafe
import me.bactoria.cafe.domain.CafeRepository
import org.springframework.stereotype.Service

@Service
class CafeService(
	private val cafeRepository: CafeRepository
) {

	suspend fun create(name: String): CafeDto {
		return Cafe.create(name = name)
			.let { cafeRepository.save(it) }
			.let { CafeDto.from(it) }
	}

	suspend fun find(cafeId: Long): CafeDto {
		return cafeRepository.load(cafeId)
			?.let { CafeDto.from(it) }
			?: throw CafeNotFoundException()
	}
}

class CafeNotFoundException : RuntimeException()
