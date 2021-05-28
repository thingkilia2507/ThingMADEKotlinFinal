package com.thing.bangkit.thingjetpackkotlin.core.helper

import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity
import com.thing.bangkit.thingjetpackkotlin.core.data.remote.FilmResponse
import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film

object FilmMapper {

//ListMapper
    fun mapResponsesToFilms(inputResponse: ArrayList<com.thing.bangkit.thingjetpackkotlin.core.data.remote.FilmResponse>): ArrayList<Film> {
        val filmList = ArrayList<Film>()
        inputResponse.map {
            val film = Film(it.id, it.overview, it.releaseDate,it.popularity,it.voteAverage,it.title,it.voteCount,it.poster,it.myType, it.favorite)
            filmList.add(film)
        }
        return filmList
    }

    fun mapEntitiesToFilms(inputEntity: ArrayList<com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity>): ArrayList<Film> {
        val filmList = ArrayList<Film>()
        inputEntity.map {
            val film = Film(it.id, it.overview, it.releaseDate,it.popularity,it.voteAverage,it.title,it.voteCount,it.poster,it.myType, it.favorite)
            filmList.add(film)
        }
        return filmList
    }

    fun mapFilmsToResponses(inputFilm: ArrayList<Film>): ArrayList<com.thing.bangkit.thingjetpackkotlin.core.data.remote.FilmResponse> {
        val filmResponseList = ArrayList<com.thing.bangkit.thingjetpackkotlin.core.data.remote.FilmResponse>()
        inputFilm.map {
            val film = com.thing.bangkit.thingjetpackkotlin.core.data.remote.FilmResponse(it.id,
                it.overview,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                it.title,
                it.voteCount,
                it.poster,
                it.myType,
                it.favorite)
            filmResponseList.add(film)
        }
        return filmResponseList
    }

    fun mapFilmsToEntities(inputFilm: List<Film>): List<com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity> {
        val filmEntityList = ArrayList<com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity>()
        inputFilm.map {
            val film =
                com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity(it.id,
                    it.overview,
                    it.releaseDate,
                    it.popularity,
                    it.voteAverage,
                    it.title,
                    it.voteCount,
                    it.poster,
                    it.myType,
                    it.favorite)
            filmEntityList.add(film)
        }
        return filmEntityList
    }

//ObjectMapper
    fun mapResponseToFilm(it: com.thing.bangkit.thingjetpackkotlin.core.data.remote.FilmResponse): Film {

        return Film(it.id,
            it.overview,
            it.releaseDate,
            it.popularity,
            it.voteAverage,
            it.title,
            it.voteCount,
            it.poster,
            it.myType,
            it.favorite)
    }

    fun mapEntityToFilm(it: com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity): Film {

        return Film(it.id,
            it.overview,
            it.releaseDate,
            it.popularity,
            it.voteAverage,
            it.title,
            it.voteCount,
            it.poster,
            it.myType,
            it.favorite)
    }

    fun mapFilmToResponse(it: Film): com.thing.bangkit.thingjetpackkotlin.core.data.remote.FilmResponse {

        return com.thing.bangkit.thingjetpackkotlin.core.data.remote.FilmResponse(it.id,
            it.overview,
            it.releaseDate,
            it.popularity,
            it.voteAverage,
            it.title,
            it.voteCount,
            it.poster,
            it.myType,
            it.favorite)
    }

    fun mapFilmToEntity(it: Film): com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity {

        return com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity(it.id,
            it.overview,
            it.releaseDate,
            it.popularity,
            it.voteAverage,
            it.title,
            it.voteCount,
            it.poster,
            it.myType,
            it.favorite)
    }

}