package com.thing.bangkit.thingjetpackkotlin.helper

import android.app.Application
import com.thing.bangkit.thingjetpackkotlin.localdb.database.FilmRoomDatabase
import com.thing.bangkit.thingjetpackkotlin.repository.FilmRepository
import com.thing.bangkit.thingjetpackkotlin.repository.LocalDataFavRepository

object Injection {
    fun provideFavRepository(application: Application): LocalDataFavRepository{
        val database = FilmRoomDatabase.getDatabase(application.applicationContext)
        return LocalDataFavRepository.getInstance(database.filmDao())
    }
    fun provideRepository(): FilmRepository {
        return FilmRepository.getInstance()
    }

}