package com.thing.bangkit.thingjetpackkotlin.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film
import com.thing.bangkit.thingjetpackkotlin.core.domain.repository.IFilmRepository

class FilmInteractor (private val filmRepository: IFilmRepository): FilmUseCase {
    override fun getAllFavMovie(): LiveData<PagedList<Film>> = filmRepository.getAllFavMovie()

    override fun getAllFavTvShow(): LiveData<PagedList<Film>> = filmRepository.getAllFavTvShow()

    override fun getFavFilmById(id: Int): Film? = filmRepository.getFavFilmById(id)

    override fun insertFavFilm(film: Film) = filmRepository.insertFavFilm(film)

    override fun deleteById(id: Int): Int = filmRepository.deleteById(id)

    override fun getMoviesList(): LiveData<ArrayList<Film>> = filmRepository.getMoviesList()

    override fun getTvShowsList(): LiveData<ArrayList<Film>> = filmRepository.getTvShowsList()

    override fun getDetailFromId(id: Int, type: Int): LiveData<Film> = filmRepository.getDetailFromId(id,type)

}