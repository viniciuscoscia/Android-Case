package br.com.viniciuscoscia.data.local.source

import br.com.viniciuscoscia.data.local.database.routecalcs.RouteCalcsDAO
import br.com.viniciuscoscia.data.local.model.RoutesCalcCache

interface RoutesCalcSource {
    suspend fun getPricesByCargoType(): List<RoutesCalcCache>
    suspend fun insertData(RoutesCalcCache: RoutesCalcCache)
    suspend fun updateData(RoutesCalcCache: RoutesCalcCache)
}

class RoutesCalcSourceImpl(private val routesCalcDao: RouteCalcsDAO)
    : RoutesCalcSource {

    override suspend fun getPricesByCargoType(): List<RoutesCalcCache>
            = routesCalcDao.getAll()

    override suspend fun insertData(RoutesCalcCache: RoutesCalcCache)
            = routesCalcDao.insert(RoutesCalcCache)

    override suspend fun updateData(RoutesCalcCache: RoutesCalcCache)
            = routesCalcDao.update(RoutesCalcCache)

}