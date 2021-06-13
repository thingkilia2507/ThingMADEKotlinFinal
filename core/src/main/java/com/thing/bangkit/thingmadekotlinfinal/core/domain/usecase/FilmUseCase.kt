package com.thing.bangkit.thingmadekotlinfinal.core.domain.usecase

import androidx.paging.PagedList
import com.thing.bangkit.thingmadekotlinfinal.core.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmUseCase {
    //LocalData (Fav)
    fun getAllFavMovie(): Flow<PagedList<Film>>
    fun getAllFavTvShow(): Flow<PagedList<Film>>
    fun getFavFilmById(id: Int): Film?
    fun insertFavFilm(film: Film)
    fun deleteById(id: Int): Int


    //RemoteData
    suspend fun getMoviesList(): Flow<ArrayList<Film>>
    suspend fun getTvShowsList(): Flow<ArrayList<Film>>
    suspend fun getDetailFromId(id: Int, type: Int): Flow<Film>
}