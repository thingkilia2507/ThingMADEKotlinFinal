package com.thing.bangkit.thingjetpackkotlin.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.thing.bangkit.thingjetpackkotlin.helper.FilmRepository
import com.thing.bangkit.thingjetpackkotlin.model.Film

class FilmViewModel(private val repository: FilmRepository) : ViewModel() {

    fun moviesData(): LiveData<ArrayList<Film>> = repository.getMoviesList()
    fun tvShowsData(): LiveData<ArrayList<Film>> = repository.getTvShowsList()
    fun getFilmsFromId(id: Int, type: Int): LiveData<Film> = repository.getDetailFromId(id, type)

}