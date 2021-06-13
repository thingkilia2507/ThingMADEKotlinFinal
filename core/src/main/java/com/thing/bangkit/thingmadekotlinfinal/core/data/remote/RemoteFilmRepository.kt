package com.thing.bangkit.thingmadekotlinfinal.core.data.remote

import android.util.Log
import com.thing.bangkit.thingmadekotlinfinal.core.BuildConfig
import com.thing.bangkit.thingmadekotlinfinal.core.helper.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteFilmRepository(private val apiService: APIService) {

    private val apiKey = BuildConfig.API_KEY

    suspend fun getMoviesList(): Flow<ArrayList<FilmResponse>> {
        return flowList(apiService.getMovieList(apiKey, "en-US"))

    }

    suspend fun getTvShowsList(): Flow<ArrayList<FilmResponse>> {
        return flowList(apiService.getTvShowList(apiKey, "en-US"))
    }

    private suspend fun flowList(response: ValuesResponse) = flow {
        try {
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(dataArray)
            }
        } catch (e: Exception) {
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getDetailFromId(id: Int, type: Int): Flow<FilmResponse> {
        val response = if (type == 1) {
            apiService.getMovie(id.toString(), apiKey, "en-US")
        } else {
            apiService.getTvShow(id.toString(), apiKey, "en-US")
        }
        return flowList(response)
    }

    private suspend fun flowList(response: FilmResponse) = flow {
        try {
            emit(response)
        } catch (e: Exception) {
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)


}