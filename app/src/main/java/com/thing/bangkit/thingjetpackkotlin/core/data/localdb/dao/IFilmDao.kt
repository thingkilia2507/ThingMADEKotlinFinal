package com.thing.bangkit.thingjetpackkotlin.core.data.localdb.dao

import androidx.paging.DataSource
import androidx.room.*
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity

@Dao
interface IFilmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(film : FilmEntity)

    @Update
    fun update(film: FilmEntity)

    @Delete
    fun delete(film: FilmEntity)

    @Query("DELETE FROM film WHERE id = :id")
    fun deleteById(id: Int) : Int

    @Query("SELECT * FROM film WHERE myType = 1 ORDER BY id ASC")
    fun getAllFavMovies() : DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM film WHERE myType = 2 ORDER BY id ASC")
    fun getAllFavTvShows(): DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM film WHERE id LIKE :id")
    fun getFavFilmByIdFilm(id :Int) : FilmEntity?

}