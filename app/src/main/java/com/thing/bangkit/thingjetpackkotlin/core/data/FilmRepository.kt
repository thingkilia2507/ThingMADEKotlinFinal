package com.thing.bangkit.thingjetpackkotlin.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.LocalDataFavRepository
import com.thing.bangkit.thingjetpackkotlin.core.data.remote.RemoteFilmRepository
import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film
import com.thing.bangkit.thingjetpackkotlin.core.domain.repository.IFilmRepository
import com.thing.bangkit.thingjetpackkotlin.core.helper.FilmMapper

class FilmRepository private constructor(
    private val remoteRepository: RemoteFilmRepository,
    private val localRepository: LocalDataFavRepository,
) : IFilmRepository {

    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(
            remoteData: RemoteFilmRepository,
            localData: LocalDataFavRepository,
        ): FilmRepository =
            instance ?: synchronized(this) {
                instance ?: FilmRepository(remoteData, localData)
            }
    }

    //LocalData (Fav)
    override fun getAllFavMovie(): LiveData<PagedList<Film>> =
        LivePagedListBuilder(localRepository.getAllFavMovie()
            .map { FilmMapper.mapEntityToFilm(it) }, 20).build()

    override fun getAllFavTvShow(): LiveData<PagedList<Film>> =
        LivePagedListBuilder(localRepository.getAllFavTvShow()
            .map { FilmMapper.mapEntityToFilm(it) }, 20).build()

    override fun getFavFilmById(id: Int): Film? {
        val film = localRepository.getFavFilmById(id)
        return if (film != null) {
            FilmMapper.mapEntityToFilm(film)
        } else {
            null
        }
    }

    override fun insertFavFilm(film: Film) = localRepository.insertFavFilm(FilmMapper.mapFilmToEntity(film))

    override fun deleteById(id: Int) = localRepository.deleteById(id)


    //RemoteData
    override fun getMoviesList(): LiveData<ArrayList<Film>> =
        remoteRepository.getMoviesList().map { FilmMapper.mapResponsesToFilms(it) }

    override fun getTvShowsList(): LiveData<ArrayList<Film>> =
        remoteRepository.getTvShowsList().map { FilmMapper.mapResponsesToFilms(it) }

    override fun getDetailFromId(id: Int, type: Int): LiveData<Film> =
        remoteRepository.getDetailFromId(id, type).map { FilmMapper.mapResponseToFilm(it) }

}