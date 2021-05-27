package com.thing.bangkit.thingjetpackkotlin.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film
import com.thing.bangkit.thingjetpackkotlin.core.domain.usecase.FilmUseCase

class FilmFavViewModel(private val useCase: FilmUseCase) : ViewModel() {

    fun favMoviesData(): LiveData<PagedList<Film>> = useCase.getAllFavMovie()
    fun favTvShowsData(): LiveData<PagedList<Film>> = useCase.getAllFavTvShow()
    fun getFavFilmFromId(id: Int): Film? = useCase.getFavFilmById(id)
    fun insertFavFilmData(film: Film)= useCase.insertFavFilm(film)
    fun deleteFavFilmFromId(id: Int)= useCase.deleteById(id)

}