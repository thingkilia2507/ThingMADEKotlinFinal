package com.thing.bangkit.thingjetpackkotlin.core.data

import android.os.Handler
import android.os.Looper
import androidx.paging.PagedList
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.LocalDataFavRepository
import com.thing.bangkit.thingjetpackkotlin.core.data.remote.RemoteFilmRepository
import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film
import com.thing.bangkit.thingjetpackkotlin.core.domain.repository.IFilmRepository
import com.thing.bangkit.thingjetpackkotlin.core.helper.FilmMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

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
    override fun getAllFavMovie(): Flow<PagedList<Film>> =
        flowOf(PagedList.Builder(localRepository.getAllFavMovie()
            .map { FilmMapper.mapEntityToFilm(it) }.create(), 20).setNotifyExecutor { Handler(Looper.getMainLooper()).post(it) }
            .setFetchExecutor { Handler(Looper.getMainLooper()).post(it)  }
            .build())


    override fun getAllFavTvShow(): Flow<PagedList<Film>> =
        flowOf(PagedList.Builder(localRepository.getAllFavTvShow()
            .map { FilmMapper.mapEntityToFilm(it) }.create(), 20).setNotifyExecutor { Handler(Looper.getMainLooper()).post(it) }
            .setFetchExecutor { Handler(Looper.getMainLooper()).post(it)  }
            .build())


    override fun getFavFilmById(id: Int): Film? {
        val film = localRepository.getFavFilmById(id)
        return if (film != null) {
            FilmMapper.mapEntityToFilm(film)
        } else {
            null
        }
    }

    override fun insertFavFilm(film: Film) =
        localRepository.insertFavFilm(FilmMapper.mapFilmToEntity(film))

    override fun deleteById(id: Int) = localRepository.deleteById(id)

    //RemoteData
    override suspend fun getMoviesList(): Flow<ArrayList<Film>> =
        remoteRepository.getMoviesList().map { FilmMapper.mapResponsesToFilms(it) }

    override suspend fun getTvShowsList(): Flow<ArrayList<Film>> =
        remoteRepository.getTvShowsList().map { FilmMapper.mapResponsesToFilms(it) }

    override suspend fun getDetailFromId(id: Int, type: Int): Flow<Film> =
        remoteRepository.getDetailFromId(id, type).map { FilmMapper.mapResponseToFilm(it) }

}