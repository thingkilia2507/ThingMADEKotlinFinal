package com.thing.bangkit.thingjetpackkotlin.core.data.remote

import android.util.Log
import com.thing.bangkit.thingjetpackkotlin.activity.DetailActivity
import com.thing.bangkit.thingjetpackkotlin.core.helper.RetrofitBuild
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteFilmRepository {

    companion object {
        const val TAG = "TagThing"
        @Volatile
        private var instance: RemoteFilmRepository? = null

        fun getInstance(): RemoteFilmRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteFilmRepository().apply { instance = this }
            }

    }
    suspend fun getMoviesList(): Flow<ArrayList<FilmResponse>> {
        return flowList(RetrofitBuild.instance().getMovieList())

    }

    suspend fun getTvShowsList(): Flow<ArrayList<FilmResponse>> {
        return flowList(RetrofitBuild.instance().getTvShowList())
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
        val response = if (type == DetailActivity.TYPE_ID_MOVIE) {
            RetrofitBuild.instance().getMovie(id.toString())
        } else {
            RetrofitBuild.instance().getTvShow(id.toString())
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