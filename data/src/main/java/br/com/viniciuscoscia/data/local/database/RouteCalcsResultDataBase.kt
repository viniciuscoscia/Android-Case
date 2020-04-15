package br.com.viniciuscoscia.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.viniciuscoscia.data.local.model.RoutesCalcCache

@Database(version = 1, entities = [RoutesCalcCache::class])
abstract class RouteCalcsResultDataBase: RoomDatabase() {

    abstract fun routeCalcsResultDAO(): RouteCalcsResultDAO

    companion object {
        fun createDataBase(context: Context): RouteCalcsResultDAO {
            return Room
                    .databaseBuilder(context, RouteCalcsResultDataBase::class.java, "routecalcs.db")
                    .build()
                    .routeCalcsResultDAO()
        }
    }
}