package br.com.viniciuscoscia.data.local.database.routecalcs

import androidx.room.*
import br.com.viniciuscoscia.data.local.model.RoutesCalcCache

@Dao
interface RouteCalcsDAO {
    @Query("SELECT * FROM routes_calc_results")
    suspend fun getAll(): List<RoutesCalcCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(routesCalcCache: RoutesCalcCache)

    @Update
    suspend fun update(routesCalcCache: RoutesCalcCache)

    @Delete
    suspend fun delete(routesCalcCache: RoutesCalcCache)
}