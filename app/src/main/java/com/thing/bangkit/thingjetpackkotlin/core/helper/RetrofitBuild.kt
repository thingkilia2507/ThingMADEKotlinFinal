package com.thing.bangkit.thingjetpackkotlin.core.helper

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuild {
    private fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

    fun instance(): APIService = Retrofit.Builder()
        .baseUrl(UtilityURL.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttpClient())
        .build()
        .create(APIService::class.java)
}