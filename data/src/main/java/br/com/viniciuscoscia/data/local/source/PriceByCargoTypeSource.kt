package br.com.viniciuscoscia.data.local.source

import br.com.viniciuscoscia.data.local.database.pricebycargotype.PriceByCargoTypeDAO
import br.com.viniciuscoscia.data.local.model.PriceByCargoTypeCache

interface PriceByCargoTypeSource {
    suspend fun getPricesByCargoType(): List<PriceByCargoTypeCache>
    suspend fun insertData(priceByCargoTypeCache: PriceByCargoTypeCache)
    suspend fun updateData(priceByCargoTypeCache: PriceByCargoTypeCache)
}

class PriceByCargoTypeSourceImpl(private val priceByCargoTypeDao: PriceByCargoTypeDAO)
    : PriceByCargoTypeSource {

    override suspend fun getPricesByCargoType(): List<PriceByCargoTypeCache>
            = priceByCargoTypeDao.getPriceByCargoType()

    override suspend fun insertData(priceByCargoTypeCache: PriceByCargoTypeCache)
            = priceByCargoTypeDao.insert(priceByCargoTypeCache)

    override suspend fun updateData(priceByCargoTypeCache: PriceByCargoTypeCache)
            = priceByCargoTypeDao.update(priceByCargoTypeCache)

}