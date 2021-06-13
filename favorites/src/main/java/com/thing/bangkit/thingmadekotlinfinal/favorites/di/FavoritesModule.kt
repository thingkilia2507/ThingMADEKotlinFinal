package com.thing.bangkit.thingmadekotlinfinal.favorites.di

import com.thing.bangkit.thingmadekotlinfinal.favorites.viemodel.FilmFavViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel { FilmFavViewModel(get()) }
}