package com.thing.bangkit.thingjetpackkotlin.helper

import com.thing.bangkit.thingjetpackkotlin.BuildConfig
import com.thing.bangkit.thingjetpackkotlin.model.Film
import com.thing.bangkit.thingjetpackkotlin.model.ValuesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("discover/movie?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    fun getMovieList(): Call<ValuesResponse>

    @GET("discover/tv?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    fun getTvShowList(): Call<ValuesResponse>

    @GET("movie/{id}?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    fun getMovie(@Path("id") id: String): Call<Film>

    @GET("tv/{id}?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    fun getTvShow(@Path("id") id: String): Call<Film>
}