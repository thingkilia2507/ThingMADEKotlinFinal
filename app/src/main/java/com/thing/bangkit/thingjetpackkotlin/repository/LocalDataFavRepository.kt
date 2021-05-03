package com.thing.bangkit.thingjetpackkotlin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.thing.bangkit.thingjetpackkotlin.localdb.dao.IFilmDao
import com.thing.bangkit.thingjetpackkotlin.model.Film

class LocalDataFavRepository private constructor(private val mIFilmDao: IFilmDao) {

    private val _moviesList = MutableLiveData<PagedList<Film>>()
    private val _tvShowsList = MutableLiveData<PagedList<Film>>()

    companion object {
        private var INSTANCE: LocalDataFavRepository? = null

        fun getInstance(filmDao: IFilmDao): LocalDataFavRepository =
            INSTANCE ?: LocalDataFavRepository(filmDao)
    }

/*    fun getAllFavMovie(): LiveData<ArrayList<Film>> {
        _moviesList.postValue(MappingHelper.mapCursorToArrayList(mIFilmDao.getAllFavMovies()))
        return _moviesList
    }

    fun getAllFavTvShow(): LiveData<ArrayList<Film>> {
        _tvShowsList.postValue( MappingHelper.mapCursorToArrayList(mIFilmDao.getAllFavTvShows()))
        return _tvShowsList

    }*/

    fun getAllFavMovie(): LiveData<PagedList<Film>> {
        return LivePagedListBuilder(mIFilmDao.getAllFavMovies(), 20).build()
    }

    fun getAllFavTvShow(): LiveData<PagedList<Film>> {
        return LivePagedListBuilder(mIFilmDao.getAllFavTvShows(), 20).build()

    }


    fun getFavFilmById(id: Int): Film? = mIFilmDao.getFavFilmByIdFilm(id)

    fun insertFavFilm(film: Film) = mIFilmDao.insert(film)

    fun deleteById(id: Int) = mIFilmDao.deleteById(id)
}