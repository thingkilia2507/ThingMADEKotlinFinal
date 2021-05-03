package com.thing.bangkit.thingjetpackkotlin.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thing.bangkit.thingjetpackkotlin.helper.Injection
import com.thing.bangkit.thingjetpackkotlin.viemodel.FilmFavViewModel
import com.thing.bangkit.thingjetpackkotlin.viemodel.FilmViewModel

class ViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(application).apply {
                    INSTANCE = this
                }
            }
    }


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FilmViewModel::class.java) -> {
                FilmViewModel(Injection.provideRepository()) as T
            }
            modelClass.isAssignableFrom(FilmFavViewModel::class.java)->{
                FilmFavViewModel(Injection.provideFavRepository(mApplication)) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}