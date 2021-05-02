package com.thing.bangkit.thingjetpackkotlin.helper

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thing.bangkit.thingjetpackkotlin.activity.DetailActivity.Companion.TYPE_ID_MOVIE
import com.thing.bangkit.thingjetpackkotlin.model.Film
import com.thing.bangkit.thingjetpackkotlin.model.ValuesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmRepository {

    private val _moviesList = MutableLiveData<ArrayList<Film>>()
    private val _tvShowsList = MutableLiveData<ArrayList<Film>>()
    private val _film = MutableLiveData<Film>()

    fun getMoviesList(): LiveData<ArrayList<Film>> {
        RetrofitBuild.instance().create(APIService::class.java).getMovieList()
            .enqueue(callbackList(1))
        return _moviesList
    }

    fun getTvShowsList(): LiveData<ArrayList<Film>> {
        RetrofitBuild.instance().create(APIService::class.java).getTvShowList()
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

    fun getDetailFromId(id: Int, type: Int): LiveData<Film> {
        if (type == TYPE_ID_MOVIE) {
            RetrofitBuild.instance().create(APIService::class.java).getMovie(id.toString())
                .enqueue(callbackFilm())
        } else {
            RetrofitBuild.instance().create(APIService::class.java).getTvShow(id.toString())
                .enqueue(callbackFilm())
        }
        return _film
    }

    private fun callbackFilm(): Callback<Film> {
        return object : Callback<Film>{
            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                if (response.isSuccessful) {
                    _film.value = response.body()
                } else {
                    Log.e(TAG, "onResponseFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Film>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        }
    }

    companion object {
        const val TAG = "TagThing"
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(): FilmRepository =
            instance ?: synchronized(this) {
                instance ?: FilmRepository().apply { instance = this }
            }

    }
}