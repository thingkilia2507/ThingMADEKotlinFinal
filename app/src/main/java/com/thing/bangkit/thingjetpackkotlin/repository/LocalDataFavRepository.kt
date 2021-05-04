package com.thing.bangkit.thingjetpackkotlin.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.thing.bangkit.thingjetpackkotlin.localdb.dao.IFilmDao
import com.thing.bangkit.thingjetpackkotlin.model.Film

class LocalDataFavRepository private constructor(private val mIFilmDao: IFilmDao) {

    companion object {
        private var INSTANCE: LocalDataFavRepository? = null

        fun getInstance(filmDao: IFilmDao): LocalDataFavRepository =
            INSTANCE ?: LocalDataFavRepository(filmDao)
    }


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