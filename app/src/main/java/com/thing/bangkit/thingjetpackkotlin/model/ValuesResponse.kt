package com.thing.bangkit.thingjetpackkotlin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ValuesResponse(
	@field:SerializedName("results")
	val results: ArrayList<Film>
) : Parcelable
