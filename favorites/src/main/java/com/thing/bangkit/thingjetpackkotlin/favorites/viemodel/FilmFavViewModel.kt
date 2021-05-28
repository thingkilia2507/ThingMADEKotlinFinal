package com.thing.bangkit.thingjetpackkotlin.favorites.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagedList
import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film
import com.thing.bangkit.thingjetpackkotlin.core.domain.usecase.FilmUseCase

class FilmFavViewModel(private val useCase: FilmUseCase) : ViewModel() {

    fun favMoviesData(): LiveData<PagedList<Film>> = useCase.getAllFavMovie().asLiveData()
    fun favTvShowsData(): LiveData<PagedList<Film>> = useCase.getAllFavTvShow().asLiveData()

}