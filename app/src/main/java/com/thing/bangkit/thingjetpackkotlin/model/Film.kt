package com.thing.bangkit.thingjetpackkotlin.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    var poster: Int,
    var title : String,
    var releaseDate : String,
    var rating : Int,
    var overview : String,
    var duration : String,
    var genre: String,
) : Parcelable