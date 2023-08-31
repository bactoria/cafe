package me.bactoria.cafe.ui

import me.bactoria.cafe.application.CafeDto
import me.bactoria.cafe.application.CafeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CafeController (
	private val cafeService: CafeService
) {

	@PostMapping("/cafes")
	suspend fun create(@RequestBody cafeCreateRequest: CafeCreateRequest): CafeDto {
		return cafeService.create(cafeCreateRequest.name)
	}
	
	data class CafeCreateRequest(
		val name: String,
	)

	@GetMapping("/cafes/{id}")
	suspend fun create(@PathVariable id: Long): CafeDto {
		return cafeService.find(id)
	}
}
