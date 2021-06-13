package com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.dao

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface IFilmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(film : com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.entity.FilmEntity)

    @Update
    fun update(film: com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.entity.FilmEntity)

    @Delete
    fun delete(film: com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.entity.FilmEntity)

    @Query("DELETE FROM film WHERE id = :id")
    fun deleteById(id: Int) : Int

    @Query("SELECT * FROM film WHERE myType = 1 ORDER BY id ASC")
    fun getAllFavMovies() : DataSource.Factory<Int, com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.entity.FilmEntity>

    @Query("SELECT * FROM film WHERE myType = 2 ORDER BY id ASC")
    fun getAllFavTvShows(): DataSource.Factory<Int, com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.entity.FilmEntity>

    @Query("SELECT * FROM film WHERE id LIKE :id")
    fun getFavFilmByIdFilm(id :Int) : com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.entity.FilmEntity?

}