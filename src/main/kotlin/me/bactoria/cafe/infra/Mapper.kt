package me.bactoria.cafe.infra

interface Mapper<T, R> {
	suspend fun toEntity(domain: T): R
	suspend fun toDomain(entity: R?): T?
}
