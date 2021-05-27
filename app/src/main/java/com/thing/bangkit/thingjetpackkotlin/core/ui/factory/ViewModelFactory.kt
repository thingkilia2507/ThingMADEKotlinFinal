package com.thing.bangkit.thingjetpackkotlin.core.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thing.bangkit.thingjetpackkotlin.core.di.Injection
import com.thing.bangkit.thingjetpackkotlin.core.domain.usecase.FilmUseCase
import com.thing.bangkit.thingjetpackkotlin.viemodel.FilmFavViewModel
import com.thing.bangkit.thingjetpackkotlin.viemodel.FilmViewModel

class ViewModelFactory private constructor(private val useCase: FilmUseCase) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    Injection.provideFilmUseCase(context)
                )

            }
    }


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FilmViewModel::class.java) -> {
                FilmViewModel(useCase) as T
            }
            modelClass.isAssignableFrom(FilmFavViewModel::class.java)->{
                FilmFavViewModel(useCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}