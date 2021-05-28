package com.thing.bangkit.thingjetpackkotlin.core.data.localdb

import androidx.paging.DataSource
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.dao.IFilmDao
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity

class LocalDataFavRepository(private val mIFilmDao: IFilmDao) {

    fun getAllFavMovie(): DataSource.Factory<Int, FilmEntity> {
//        return LivePagedListBuilder(mIFilmDao.getAllFavMovies(), 20).build()
        return mIFilmDao.getAllFavMovies()
    }

    fun getAllFavTvShow(): DataSource.Factory<Int, FilmEntity> {
//        return LivePagedListBuilder(mIFilmDao.getAllFavTvShows(), 20).build()
        return mIFilmDao.getAllFavTvShows()
    }


    fun getFavFilmById(id: Int): FilmEntity? = mIFilmDao.getFavFilmByIdFilm(id)

    fun insertFavFilm(film: FilmEntity) = mIFilmDao.insert(film)

    fun deleteById(id: Int) = mIFilmDao.deleteById(id)
}