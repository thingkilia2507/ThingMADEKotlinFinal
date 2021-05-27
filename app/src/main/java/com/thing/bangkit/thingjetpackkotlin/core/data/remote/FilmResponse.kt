package com.thing.bangkit.thingjetpackkotlin.core.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date", alternate = ["first_air_date"])
    val releaseDate: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("title", alternate = ["name"])
    val title: String,

    @field:SerializedName("vote_count")
    val voteCount: Int,

    @field:SerializedName("poster_path")
    val poster: String,

    var myType: Int,

    var favorite: Boolean = false
) : Parcelable

