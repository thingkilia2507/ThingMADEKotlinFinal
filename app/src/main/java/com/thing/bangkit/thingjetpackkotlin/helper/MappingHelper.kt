package com.thing.bangkit.thingjetpackkotlin.helper

import android.database.Cursor
import com.thing.bangkit.thingjetpackkotlin.model.Film

object MappingHelper {
    fun mapCursorToArrayList(filmCursor: Cursor?): ArrayList<Film> {
        val filmList = ArrayList<Film>()

        filmCursor?.apply {
            while (moveToNext()) {

                val id: Int = getInt(getColumnIndexOrThrow("id"))
                val overview: String = getString(getColumnIndexOrThrow("overview"))
                val releaseDate: String = getString(getColumnIndexOrThrow("releaseDate"))
                val popularity: Double = getDouble(getColumnIndexOrThrow("popularity"))
                val voteAverage: Double = getDouble(getColumnIndexOrThrow("voteAverage"))
                val title: String = getString(getColumnIndexOrThrow("title"))
                val voteCount: Int = getInt(getColumnIndexOrThrow("voteCount"))
                val poster: String = getString(getColumnIndexOrThrow("poster"))
                val type: Int = getInt(getColumnIndexOrThrow("myType"))
                val film = Film(
                    id,
                    overview,
                    releaseDate,
                    popularity,
                    voteAverage,
                    title,
                    voteCount,
                    poster,
                    type
                )
                filmList.add(film)
            }
        }

        return filmList
    }

    fun mapCursorToObject(filmCursor: Cursor?): Film {
        filmCursor?.apply {
            if (moveToFirst()) {
                val id: Int = getInt(getColumnIndexOrThrow("id"))
                val overview: String = getString(getColumnIndexOrThrow("overview"))
                val releaseDate: String = getString(getColumnIndexOrThrow("releaseDate"))
                val popularity: Double = getDouble(getColumnIndexOrThrow("popularity"))
                val voteAverage: Double = getDouble(getColumnIndexOrThrow("voteAverage"))
                val title: String = getString(getColumnIndexOrThrow("title"))
                val voteCount: Int = getInt(getColumnIndexOrThrow("voteCount"))
                val poster: String = getString(getColumnIndexOrThrow("poster"))
                val type: Int = getInt(getColumnIndexOrThrow("myType"))
                return Film(
                    id,
                    overview,
                    releaseDate,
                    popularity,
                    voteAverage,
                    title,
                    voteCount,
                    poster,
                    type
                )
            }
        }
        return Film(0, "", "", 0.0, 0.0, "", 0, "", -1)
    }

}