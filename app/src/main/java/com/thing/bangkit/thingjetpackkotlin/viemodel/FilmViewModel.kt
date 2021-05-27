package com.thing.bangkit.thingjetpackkotlin.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film
import com.thing.bangkit.thingjetpackkotlin.core.domain.usecase.FilmUseCase

class FilmViewModel (private val useCase: FilmUseCase) : ViewModel() {

    fun moviesData(): LiveData<ArrayList<Film>> = useCase.getMoviesList()
    fun tvShowsData(): LiveData<ArrayList<Film>> = useCase.getTvShowsList()
    fun getFilmsFromId(id: Int, type: Int): LiveData<Film> = useCase.getDetailFromId(id, type)

}