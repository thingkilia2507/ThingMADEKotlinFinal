package com.thing.bangkit.thingjetpackkotlin.core.data.localdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.dao.IFilmDao
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity

@Database(entities = [FilmEntity::class], version = 1, exportSchema = false)
abstract class FilmRoomDatabase : RoomDatabase() {
    abstract fun filmDao(): IFilmDao

    companion object{
        private const val DATABASE_NAME = "film_fav_database"

        @Volatile
        private var INSTANCE : FilmRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FilmRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FilmRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, FilmRoomDatabase::class.java, DATABASE_NAME).build()

                }
            }
            return INSTANCE as FilmRoomDatabase
        }

    }

}