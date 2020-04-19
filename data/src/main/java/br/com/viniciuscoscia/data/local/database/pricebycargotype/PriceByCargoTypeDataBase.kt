package br.com.viniciuscoscia.data.local.database.pricebycargotype

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.viniciuscoscia.data.local.model.PriceByCargoTypeCache

@Database(version = 1, entities = [PriceByCargoTypeCache::class])
abstract class PriceByCargoTypeDataBase: RoomDatabase() {

    abstract fun routeCalcsResultDAO(): PriceByCargoTypeDAO

    companion object {
        fun createDataBase(context: Context): PriceByCargoTypeDAO {
            return Room
                    .databaseBuilder(context, PriceByCargoTypeDataBase::class.java, "routecalcs.db")
                    .build()
                    .routeCalcsResultDAO()
        }
    }
}