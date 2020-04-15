package br.com.viniciuscoscia.data.local.database

import androidx.room.*
import br.com.viniciuscoscia.data.local.model.RoutesCalcCache

@Dao
interface RouteCalcsResultDAO {
    @Query("SELECT * FROM routes_calc_results")
    suspend fun getRouteCalcsResult(): List<RoutesCalcCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRouteCalcsResult(routesCalcCache: RoutesCalcCache)

    @Update
    suspend fun updateRouteCalcsResult(routesCalcCache: RoutesCalcCache)

    @Delete
    suspend fun deleteRouteCalcsResult(routesCalcCache: RoutesCalcCache)
}