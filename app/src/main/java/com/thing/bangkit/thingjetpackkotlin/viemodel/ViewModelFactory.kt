package com.thing.bangkit.thingjetpackkotlin.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thing.bangkit.thingjetpackkotlin.helper.FilmRepository
import com.thing.bangkit.thingjetpackkotlin.helper.Injection

class ViewModelFactory private constructor(private val mFilmRepository: FilmRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository()).apply {
                    instance = this
                }
            }
    }


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(FilmViewModel::class.java) -> {
                return FilmViewModel(mFilmRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}