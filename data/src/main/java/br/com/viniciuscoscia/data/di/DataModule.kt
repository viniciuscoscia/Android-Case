package br.com.viniciuscoscia.data.di

import org.koin.dsl.module.module

val repositoryModule = module {
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)