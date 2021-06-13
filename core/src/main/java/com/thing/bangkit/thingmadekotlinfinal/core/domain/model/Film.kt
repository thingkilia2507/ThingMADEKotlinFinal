package com.thing.bangkit.thingmadekotlinfinal.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: Int,
    val overview: String,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val title: String,
    val voteCount: Int,
    val poster: String,
    var myType: Int,
    var favorite: Boolean = false
) : Parcelable

