package com.thing.bangkit.thingjetpackkotlin.core.data.localdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.dao.IFilmDao
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity

@Database(entities = [FilmEntity::class], version = 1, exportSchema = false)
abstract class FilmRoomDatabase : RoomDatabase() {
    abstract fun filmDao(): IFilmDao

    companion object{
        const val DATABASE_NAME = "film_fav_database"
    }

}