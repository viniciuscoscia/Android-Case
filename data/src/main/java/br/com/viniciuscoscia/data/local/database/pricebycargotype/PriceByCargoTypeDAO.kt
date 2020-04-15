package br.com.viniciuscoscia.data.local.database.pricebycargotype

import androidx.room.*
import br.com.viniciuscoscia.data.local.model.PriceByCargoTypeCache

@Dao
interface PriceByCargoTypeDAO {
    @Query("SELECT * FROM routes_calc_results")
    suspend fun getPriceByCargoType(): List<PriceByCargoTypeCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(PriceByCargoType: PriceByCargoTypeCache)

    @Update
    suspend fun update(PriceByCargoType: PriceByCargoTypeCache)

    @Delete
    suspend fun delete(PriceByCargoType: PriceByCargoTypeCache)
}