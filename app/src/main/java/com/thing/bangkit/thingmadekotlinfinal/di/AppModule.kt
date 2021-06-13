package com.thing.bangkit.thingmadekotlinfinal.di

import com.thing.bangkit.thingmadekotlinfinal.core.domain.usecase.FilmInteractor
import com.thing.bangkit.thingmadekotlinfinal.core.domain.usecase.FilmUseCase
import com.thing.bangkit.thingmadekotlinfinal.viemodel.DetailViewModel
import com.thing.bangkit.thingmadekotlinfinal.viemodel.FilmViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FilmUseCase> { FilmInteractor(get()) }
}

val viewModelModule = module {
    viewModel { FilmViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
