package br.com.viniciuscoscia.data.di

import br.com.viniciuscoscia.data.TruckPadRepository
import br.com.viniciuscoscia.truckpad.domain.repositories.ITruckPadRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule = module {
    factory<ITruckPadRepository> {
        TruckPadRepository(priceByCargoTypeCache = get(),
                priceByCargoTypeSourceRemote = get(),
                routesCalcCache = get(),
                routesCalcSourceRemote = get())
    }
}

val dataModules: List<Module> = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)