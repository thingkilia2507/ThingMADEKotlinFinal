package com.thing.bangkit.thingjetpackkotlin.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film

interface FilmUseCase {
    //LocalData (Fav)
    fun getAllFavMovie(): LiveData<PagedList<Film>>

    fun getAllFavTvShow(): LiveData<PagedList<Film>>

    fun getFavFilmById(id: Int): Film?

    fun insertFavFilm(film: Film)
    fun deleteById(id: Int): Int


    //RemoteData
    fun getMoviesList(): LiveData<ArrayList<Film>>

    fun getTvShowsList(): LiveData<ArrayList<Film>>

    fun getDetailFromId(id: Int, type: Int): LiveData<Film>
}