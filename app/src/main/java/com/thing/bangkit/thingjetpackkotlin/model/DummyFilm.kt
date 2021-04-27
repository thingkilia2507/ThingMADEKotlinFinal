package com.thing.bangkit.thingjetpackkotlin.model

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.thing.bangkit.thingjetpackkotlin.R
import com.thing.bangkit.thingjetpackkotlin.helper.DateGenerator

object DummyFilm {

    private val moviesList = MutableLiveData<ArrayList<Film>>()
    private val tvShowsList = MutableLiveData<ArrayList<Film>>()
    @SuppressLint("Recycle")
    fun getGenerateDummyMovies(context: Context) : ArrayList<Film> {
        val movies = ArrayList<Film>()
        val titles = context.resources.getStringArray(R.array.movie_title)
        val durations = context.resources.getStringArray(R.array.movie_duration)
        val genres = context.resources.getStringArray(R.array.movie_genre)
        val overviews = context.resources.getStringArray(R.array.movie_overview)
        val posters = context.resources.obtainTypedArray(R.array.movie_poster)
        val ratings = context.resources.getIntArray(R.array.movie_rating)
        val releaseDates = context.resources.getStringArray(R.array.movie_releaseDate)

        for (i in titles.indices) {
            movies.add(Film(posters.getResourceId(i, -1),
                titles[i],
                DateGenerator.modifyDateStringFormat(releaseDates[i]),
                ratings[i],
                overviews[i],
                durations[i],
                genres[i]))
        }
        moviesList.postValue(movies)
        return movies
    }

    @SuppressLint("Recycle")
    fun getGenerateDummyTvShows(context: Context) : ArrayList<Film>{
        val tvShows = ArrayList<Film>()
        val titles = context.resources.getStringArray(R.array.tvshow_title)
        val durations = context.resources.getStringArray(R.array.tvshow_duration)
        val genres = context.resources.getStringArray(R.array.tvshow_genre)
        val overviews = context.resources.getStringArray(R.array.tvshow_overview)
        val posters = context.resources.obtainTypedArray(R.array.tvshow_poster)
        val ratings = context.resources.getIntArray(R.array.tvshow_rating)
        val releaseDates = context.resources.getStringArray(R.array.tvshow_releaseDate)

        for (i in titles.indices) {
            tvShows.add(Film(posters.getResourceId(i, -1),
                titles[i],
                releaseDates[i],
                ratings[i],
                overviews[i],
                durations[i],
                genres[i]))
        }
        tvShowsList.postValue(tvShows)
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