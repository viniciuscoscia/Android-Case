package br.com.viniciuscoscia.data.local.source

import br.com.viniciuscoscia.data.local.database.pricebycargotype.PriceByCargoTypeDAO
import br.com.viniciuscoscia.data.local.model.PriceByCargoTypeCache

interface PriceByCargoTypeDataSourceCache {
    suspend fun getPricesByCargoType(): List<PriceByCargoTypeCache>
    suspend fun insertData(priceByCargoTypeCache: PriceByCargoTypeCache)
    suspend fun updateData(priceByCargoTypeCache: PriceByCargoTypeCache)
    suspend fun find(id: Int): PriceByCargoTypeCache
}

class PriceByCargoTypeSourceCacheImpl(private val priceByCargoTypeDao: PriceByCargoTypeDAO)
    : PriceByCargoTypeDataSourceCache {

    override suspend fun getPricesByCargoType(): List<PriceByCargoTypeCache>
            = priceByCargoTypeDao.getPriceByCargoType()

    override suspend fun insertData(priceByCargoTypeCache: PriceByCargoTypeCache)
            = priceByCargoTypeDao.insert(priceByCargoTypeCache)

    override suspend fun updateData(priceByCargoTypeCache: PriceByCargoTypeCache)
            = priceByCargoTypeDao.update(priceByCargoTypeCache)

    override suspend fun find(id: Int): PriceByCargoTypeCache {
        return priceByCargoTypeDao.find(id)
    }

}