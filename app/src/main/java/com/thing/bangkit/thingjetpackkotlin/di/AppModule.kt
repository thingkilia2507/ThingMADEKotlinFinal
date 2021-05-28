package com.thing.bangkit.thingjetpackkotlin.di

import com.thing.bangkit.thingjetpackkotlin.core.domain.usecase.FilmInteractor
import com.thing.bangkit.thingjetpackkotlin.core.domain.usecase.FilmUseCase
import com.thing.bangkit.thingjetpackkotlin.viemodel.DetailViewModel
import com.thing.bangkit.thingjetpackkotlin.viemodel.FilmViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FilmUseCase> { FilmInteractor(get()) }
}

val viewModelModule = module {
    viewModel { FilmViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
