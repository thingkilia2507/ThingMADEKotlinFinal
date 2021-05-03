package com.thing.bangkit.thingjetpackkotlin.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.thing.bangkit.thingjetpackkotlin.R
import com.thing.bangkit.thingjetpackkotlin.helper.DateGenerator

object DummyFilm {

    private val moviesList = MutableLiveData<ArrayList<Film>>()
    private val tvShowsList = MutableLiveData<ArrayList<Film>>()
    fun getGenerateDummyMovies(context: Context) : ArrayList<Film> {
        val movies = ArrayList<Film>()
        val titles = context.resources.getStringArray(R.array.movie_title)
        val overviews = context.resources.getStringArray(R.array.movie_overview)
        val posters = context.resources.obtainTypedArray(R.array.movie_poster)
        val ratings = context.resources.getIntArray(R.array.movie_rating)
        val releaseDates = context.resources.getStringArray(R.array.movie_releaseDate)

        for (i in titles.indices) {
            movies.add(Film(
                i,
                overviews[i],
                DateGenerator.modifyDateStringFormat(releaseDates[i]),
                80.0,
                ratings[i].toDouble()/10,
                titles[i],
                120,
                posters.getResourceId(i, -1).toString(),1))
        }
        moviesList.postValue(movies)
        posters.recycle()
        return movies
    }

    fun getGenerateDummyTvShows(context: Context) : ArrayList<Film>{
        val tvShows = ArrayList<Film>()
        val titles = context.resources.getStringArray(R.array.tvshow_title)
        val overviews = context.resources.getStringArray(R.array.tvshow_overview)
        val posters = context.resources.obtainTypedArray(R.array.tvshow_poster)
        val ratings = context.resources.getIntArray(R.array.tvshow_rating)
        val releaseDates = context.resources.getStringArray(R.array.tvshow_releaseDate)

        for (i in titles.indices) {
            tvShows.add(Film(
                i,
                overviews[i],
                DateGenerator.modifyDateStringFormat(releaseDates[i]),
                80.0*i,
                ratings[i].toDouble()/10,
                titles[i],
                120*i,
                posters.getResourceId(i, -1).toString(),2))
        }
        tvShowsList.postValue(tvShows)
        posters.recycle()
        return tvShows
    }

    fun getFilmFromId(id: Int, type: Int): Film? {
        return if(type == 1){
            moviesList.value?.get(id)
        } else {
            tvShowsList.value?.get(id)
        }


    }


}