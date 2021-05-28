package com.thing.bangkit.thingjetpackkotlin.core.domain.usecase

import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film
import com.thing.bangkit.thingjetpackkotlin.core.domain.repository.IFilmRepository

class FilmInteractor (private val filmRepository: IFilmRepository): FilmUseCase {
    override fun getAllFavMovie()= filmRepository.getAllFavMovie()

    override fun getAllFavTvShow()= filmRepository.getAllFavTvShow()

    override fun getFavFilmById(id: Int) = filmRepository.getFavFilmById(id)

    override fun insertFavFilm(film: Film) = filmRepository.insertFavFilm(film)

    override fun deleteById(id: Int) = filmRepository.deleteById(id)

    override suspend fun getMoviesList() = filmRepository.getMoviesList()

    override suspend fun getTvShowsList() = filmRepository.getTvShowsList()

    override suspend fun getDetailFromId(id: Int, type: Int) = filmRepository.getDetailFromId(id,type)

}