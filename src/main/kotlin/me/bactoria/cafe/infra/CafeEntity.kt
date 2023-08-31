package me.bactoria.cafe.infra

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("cafe")
data class CafeEntity(
	@Id
	val id: Long,
	val name: String,
)
