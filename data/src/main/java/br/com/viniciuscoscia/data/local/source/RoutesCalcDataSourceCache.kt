package br.com.viniciuscoscia.data.local.source

import br.com.viniciuscoscia.data.local.database.routecalcs.RouteCalcsDAO
import br.com.viniciuscoscia.data.local.model.RoutesCalcCache

interface RoutesCalcDataSourceCache {
    suspend fun getPricesByCargoType(): List<RoutesCalcCache>
    suspend fun insertData(RoutesCalcCache: RoutesCalcCache)
    suspend fun updateData(RoutesCalcCache: RoutesCalcCache)
}

class RoutesCalcDataSourceCacheImpl(private val routesCalcDao: RouteCalcsDAO)
    : RoutesCalcDataSourceCache {

    override suspend fun getPricesByCargoType(): List<RoutesCalcCache>
            = routesCalcDao.getAll()

    override suspend fun insertData(RoutesCalcCache: RoutesCalcCache)
            = routesCalcDao.insert(RoutesCalcCache)

    override suspend fun updateData(RoutesCalcCache: RoutesCalcCache)
            = routesCalcDao.update(RoutesCalcCache)

}