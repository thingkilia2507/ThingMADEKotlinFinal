package com.thing.bangkit.thingjetpackkotlin.core.data.localdb

import androidx.paging.DataSource

class LocalDataFavRepository(private val mIFilmDao: com.thing.bangkit.thingjetpackkotlin.core.data.localdb.dao.IFilmDao) {

    fun getAllFavMovie(): DataSource.Factory<Int, com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity> {
        return mIFilmDao.getAllFavMovies()
    }

    fun getAllFavTvShow(): DataSource.Factory<Int, com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity> {
        return mIFilmDao.getAllFavTvShows()
    }


    fun getFavFilmById(id: Int): com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity? = mIFilmDao.getFavFilmByIdFilm(id)

    fun insertFavFilm(film: com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity) = mIFilmDao.insert(film)

    fun deleteById(id: Int) = mIFilmDao.deleteById(id)
}