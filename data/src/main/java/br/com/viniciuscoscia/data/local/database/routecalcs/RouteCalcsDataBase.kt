package br.com.viniciuscoscia.data.local.database.routecalcs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.viniciuscoscia.data.local.model.RoutesCalcCache

@Database(version = 1, entities = [RoutesCalcCache::class])
abstract class RouteCalcsDataBase: RoomDatabase() {

    abstract fun routeCalcsResultDAO(): RouteCalcsDAO

    companion object {
        fun createDataBase(context: Context): RouteCalcsDAO {
            return Room
                    .databaseBuilder(context, RouteCalcsDataBase::class.java, "routecalcs.db")
                    .build()
                    .routeCalcsResultDAO()
        }
    }
}