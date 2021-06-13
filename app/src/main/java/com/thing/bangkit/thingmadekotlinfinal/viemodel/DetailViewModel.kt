package com.thing.bangkit.thingmadekotlinfinal.viemodel

import androidx.lifecycle.ViewModel
import com.thing.bangkit.thingmadekotlinfinal.core.domain.model.Film
import com.thing.bangkit.thingmadekotlinfinal.core.domain.usecase.FilmUseCase

class DetailViewModel(private val useCase: FilmUseCase) : ViewModel() {

    fun getFavFilmFromId(id: Int): Film? = useCase.getFavFilmById(id)
    fun insertFavFilmData(film: Film)= useCase.insertFavFilm(film)
    fun deleteFavFilmFromId(id: Int)= useCase.deleteById(id)

}