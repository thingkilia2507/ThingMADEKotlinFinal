package com.thing.bangkit.thingjetpackkotlin.localdb.dao

import androidx.paging.DataSource
import androidx.room.*
import com.thing.bangkit.thingjetpackkotlin.model.Film

@Dao
interface IFilmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(film : Film)

    @Update
    fun update(film: Film)

    @Delete
    fun delete(film: Film)

    @Query("DELETE FROM Film WHERE id = :id")
    fun deleteById(id: Int) : Int

    @Query("SELECT * FROM Film WHERE myType = 1 ORDER BY id ASC")
    fun getAllFavMovies() : DataSource.Factory<Int,Film>

    @Query("SELECT * FROM Film WHERE myType = 2 ORDER BY id ASC")
    fun getAllFavTvShows(): DataSource.Factory<Int,Film>

    @Query("SELECT * FROM Film WHERE id LIKE :id")
    fun getFavFilmByIdFilm(id :Int) : Film?

}