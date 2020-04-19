package br.com.viniciuscoscia.data.di

import br.com.viniciuscoscia.data.local.database.pricebycargotype.PriceByCargoTypeDataBase
import br.com.viniciuscoscia.data.local.database.routecalcs.RouteCalcsDataBase
import br.com.viniciuscoscia.data.local.source.PriceByCargoTypeDataSourceCache
import br.com.viniciuscoscia.data.local.source.PriceByCargoTypeSourceCacheImpl
import br.com.viniciuscoscia.data.local.source.RoutesCalcDataSourceCache
import br.com.viniciuscoscia.data.local.source.RoutesCalcDataSourceCacheImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataModule = module {
    single { PriceByCargoTypeDataBase.createDataBase(androidContext()) }
    factory<PriceByCargoTypeDataSourceCache> { PriceByCargoTypeSourceCacheImpl(priceByCargoTypeDao = get()) }

    single { RouteCalcsDataBase.createDataBase(androidContext()) }
    factory<RoutesCalcDataSourceCache> { RoutesCalcDataSourceCacheImpl(routesCalcDao = get()) }
}