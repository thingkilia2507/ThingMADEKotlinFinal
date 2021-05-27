package com.thing.bangkit.thingjetpackkotlin.core.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thing.bangkit.thingjetpackkotlin.activity.DetailActivity.Companion.TYPE_ID_MOVIE
import com.thing.bangkit.thingjetpackkotlin.core.helper.RetrofitBuild
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteFilmRepository {

    private val _moviesList = MutableLiveData<ArrayList<FilmResponse>>()
    private val _tvShowsList = MutableLiveData<ArrayList<FilmResponse>>()
    private val _film = MutableLiveData<FilmResponse>()

    fun getMoviesList(): LiveData<ArrayList<FilmResponse>> {
        RetrofitBuild.instance().getMovieList()
            .enqueue(callbackList(1))
        return _moviesList
    }

    fun getTvShowsList(): LiveData<ArrayList<FilmResponse>> {
        RetrofitBuild.instance().getTvShowList()
            .enqueue(callbackList(2))
        return _tvShowsList
    }

    private fun callbackList(type: Int): Callback<ValuesResponse> {
        return object : Callback<ValuesResponse> {
            override fun onResponse(
                call: Call<ValuesResponse>,
                response: Response<ValuesResponse>,
            ) {
                if (response.isSuccessful) {
                    if(type == 1) _moviesList.value = response.body()?.results
                    else _tvShowsList.value = response.body()?.results
                } else {
                    Log.e(TAG, "onResponseFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ValuesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        }

    }

    fun getDetailFromId(id: Int, type: Int): LiveData<FilmResponse> {
        if (type == TYPE_ID_MOVIE) {
            RetrofitBuild.instance().getMovie(id.toString())
                .enqueue(callbackFilm())
        } else {
            RetrofitBuild.instance().getTvShow(id.toString())
                .enqueue(callbackFilm())
        }
        return _film
    }

    private fun callbackFilm(): Callback<FilmResponse> {
        return object : Callback<FilmResponse>{
            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                if (response.isSuccessful) {
                    _film.value = response.body()
                } else {
                    Log.e(TAG, "onResponseFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        }
    }

    companion object {
        const val TAG = "TagThing"
        @Volatile
        private var instance: RemoteFilmRepository? = null

        fun getInstance(): RemoteFilmRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteFilmRepository().apply { instance = this }
            }

    }
}