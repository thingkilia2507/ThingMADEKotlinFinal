package com.thing.bangkit.thingjetpackkotlin.core.helper

import com.thing.bangkit.thingjetpackkotlin.BuildConfig
import com.thing.bangkit.thingjetpackkotlin.core.data.remote.FilmResponse
import com.thing.bangkit.thingjetpackkotlin.core.data.remote.ValuesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("discover/movie?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    suspend fun getMovieList(): ValuesResponse

    @GET("discover/tv?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    suspend fun getTvShowList(): ValuesResponse

    @GET("movie/{id}?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    suspend fun getMovie(@Path("id") id: String): FilmResponse

    @GET("tv/{id}?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    suspend fun getTvShow(@Path("id") id: String): FilmResponse
}