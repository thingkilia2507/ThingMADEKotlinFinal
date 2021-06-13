package com.thing.bangkit.thingmadekotlinfinal.core.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object Utility {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    fun checkNetworkConnection(context: Context): NetworkCapabilities? {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        return connectivityManager.getNetworkCapabilities(network)
    }
}