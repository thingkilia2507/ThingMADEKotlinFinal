package com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "film")
@Parcelize
data class FilmEntity(
    @PrimaryKey
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

