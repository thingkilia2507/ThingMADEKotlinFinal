package com.thing.bangkit.thingjetpackkotlin.core.di  //di = dependency injection

import android.content.Context
import com.thing.bangkit.thingjetpackkotlin.core.data.FilmRepository
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.LocalDataFavRepository
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.database.FilmRoomDatabase
import com.thing.bangkit.thingjetpackkotlin.core.data.remote.RemoteFilmRepository
import com.thing.bangkit.thingjetpackkotlin.core.domain.repository.IFilmRepository
import com.thing.bangkit.thingjetpackkotlin.core.domain.usecase.FilmInteractor
import com.thing.bangkit.thingjetpackkotlin.core.domain.usecase.FilmUseCase

object Injection {
    private fun provideFavRepository(context: Context): LocalDataFavRepository {
        val database = FilmRoomDatabase.getDatabase(context)
        return LocalDataFavRepository.getInstance(database.filmDao())
    }
    private fun provideRemoteRepository(): RemoteFilmRepository {
        return RemoteFilmRepository.getInstance()
    }

    private fun provideRepository(context: Context): IFilmRepository {
        return FilmRepository.getInstance(provideRemoteRepository(), provideFavRepository(context))
    }


    fun provideFilmUseCase(context: Context): FilmUseCase {
        val repository = provideRepository(context)
        return FilmInteractor(repository)
    }

}