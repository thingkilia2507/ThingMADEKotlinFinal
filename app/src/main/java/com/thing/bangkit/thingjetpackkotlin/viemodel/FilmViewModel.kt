package com.thing.bangkit.thingjetpackkotlin.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film
import com.thing.bangkit.thingjetpackkotlin.core.domain.usecase.FilmUseCase

class FilmViewModel (private val useCase: FilmUseCase) : ViewModel() {

    suspend fun moviesData(): LiveData<ArrayList<Film>> = useCase.getMoviesList().asLiveData()
    suspend fun tvShowsData(): LiveData<ArrayList<Film>> = useCase.getTvShowsList().asLiveData()
    suspend fun getFilmsFromId(id: Int, type: Int): LiveData<Film> = useCase.getDetailFromId(id, type).asLiveData()

}