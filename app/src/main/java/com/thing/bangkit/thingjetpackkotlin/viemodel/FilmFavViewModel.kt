package com.thing.bangkit.thingjetpackkotlin.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.thing.bangkit.thingjetpackkotlin.model.Film
import com.thing.bangkit.thingjetpackkotlin.repository.LocalDataFavRepository

class FilmFavViewModel(private val repository : LocalDataFavRepository) : ViewModel() {

    fun favMoviesData(): LiveData<PagedList<Film>> = repository.getAllFavMovie()
    fun favTvShowsData(): LiveData<PagedList<Film>> = repository.getAllFavTvShow()
    fun getFavFilmFromId(id: Int): Film? = repository.getFavFilmById(id)
    fun insertFavFilmData(film: Film)= repository.insertFavFilm(film)
    fun deleteFavFilmFromId(id: Int)= repository.deleteById(id)

}