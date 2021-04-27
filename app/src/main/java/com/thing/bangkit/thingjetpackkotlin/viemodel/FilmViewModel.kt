package com.thing.bangkit.thingjetpackkotlin.viemodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.thing.bangkit.thingjetpackkotlin.model.DummyFilm
import com.thing.bangkit.thingjetpackkotlin.model.Film

class FilmViewModel : ViewModel() {
    fun getGenerateMoviesData(context: Context) : ArrayList<Film> = DummyFilm.getGenerateDummyMovies(context)
    fun getGenerateTvShowData(context: Context) : ArrayList<Film> = DummyFilm.getGenerateDummyTvShows(context)
    fun getFilmsFromId(id: Int, type :Int) : Film? = DummyFilm.getFilmFromId(id, type)
}