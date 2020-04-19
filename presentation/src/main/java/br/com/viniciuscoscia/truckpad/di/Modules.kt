package br.com.viniciuscoscia.truckpad.di

import br.com.viniciuscoscia.truckpad.ui.main.fragment.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module(override = true) {
    viewModel {
        HomeViewModel(androidContext(),
                getRouteCalcsUseCase = get(),
                getPricesByCargoTypeUseCase = get())
    }
}