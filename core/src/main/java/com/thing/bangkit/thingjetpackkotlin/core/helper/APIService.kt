package com.thing.bangkit.thingjetpackkotlin.core.helper

import com.thing.bangkit.thingjetpackkotlin.core.BuildConfig
import com.thing.bangkit.thingjetpackkotlin.core.data.remote.FilmResponse
import com.thing.bangkit.thingjetpackkotlin.core.data.remote.ValuesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("discover/movie")
    suspend fun getMovieList(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
    ): ValuesResponse

    @GET("discover/tv")
    suspend fun getTvShowList(
        @Query("api_key") api_key: String,
        @Query("language") language: String): ValuesResponse

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") id: String,
                         @Query("api_key") api_key: String,
                         @Query("language") language: String): FilmResponse

    @GET("tv/{id}")
    suspend fun getTvShow(@Path("id") id: String,
                          @Query("api_key") api_key: String,
                          @Query("language") language: String): FilmResponse
}