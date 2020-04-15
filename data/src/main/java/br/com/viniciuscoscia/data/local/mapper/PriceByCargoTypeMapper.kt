package br.com.viniciuscoscia.data.local.mapper

import br.com.viniciuscoscia.data.local.model.PriceByCargoTypeCache
import br.com.viniciuscoscia.truckpad.domain.entities.PriceByCargoType

object PriceByCargoTypeMapper {
    fun map(cacheData: List<PriceByCargoTypeCache>) = cacheData.map { map(it) }

    private fun map(cacheData: PriceByCargoTypeCache) = PriceByCargoType(
            frigorificada = cacheData.frigorificada,
            geral = cacheData.geral,
            granel = cacheData.granel,
            neogranel = cacheData.neogranel,
            perigosa = cacheData.perigosa
    )

    fun mapToCache(jobs: List<PriceByCargoType>) = jobs.map { map(it) }

    private fun map(data: PriceByCargoType) = PriceByCargoTypeCache(
            frigorificada = data.frigorificada,
            geral = data.geral,
            granel = data.granel,
            neogranel = data.neogranel,
            perigosa = data.perigosa
    )
}