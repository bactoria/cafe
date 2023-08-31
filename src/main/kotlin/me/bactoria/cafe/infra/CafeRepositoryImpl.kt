package me.bactoria.cafe.infra

import me.bactoria.cafe.domain.Cafe
import me.bactoria.cafe.domain.CafeRepository
import org.springframework.stereotype.Repository

@Repository
class CafeRepositoryImpl(
	private val cafeDao: CafeDao
) : CafeRepository {

	override suspend fun save(cafe: Cafe): Cafe {
		return CafeMapper.toEntity(cafe)
			.let { cafeDao.save(it) }
			.let { CafeMapper.toDomain(it)!! }
	}

	override suspend fun load(cafeId: Long): Cafe? {
		return cafeDao.findById(cafeId)
			.let { CafeMapper.toDomain(it) }
	}
}

object CafeMapper : Mapper<Cafe, CafeEntity> {
	override suspend fun toEntity(domain: Cafe): CafeEntity =
		domain.run {
			CafeEntity(
				id = id,
				name = name,
			)
		}

	override suspend fun toDomain(entity: CafeEntity?): Cafe? =
		entity?.run {
			Cafe(
				id = id,
				name = name,
			)
		}
}
