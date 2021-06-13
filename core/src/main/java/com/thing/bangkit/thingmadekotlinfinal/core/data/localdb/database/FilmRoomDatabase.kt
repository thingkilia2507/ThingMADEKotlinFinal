package com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.entity.FilmEntity::class], version = 1, exportSchema = false)
abstract class FilmRoomDatabase : RoomDatabase() {
    abstract fun filmDao(): com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.dao.IFilmDao

    companion object{
        const val DATABASE_NAME = "film_fav_database"
    }

}