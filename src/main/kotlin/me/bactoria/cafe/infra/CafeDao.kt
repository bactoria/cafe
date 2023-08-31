package me.bactoria.cafe.infra

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CafeDao : CoroutineCrudRepository<CafeEntity, Long> {
}
