package com.thing.bangkit.thingjetpackkotlin.favorites.di

import com.thing.bangkit.thingjetpackkotlin.favorites.viemodel.FilmFavViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel { FilmFavViewModel(get()) }
}