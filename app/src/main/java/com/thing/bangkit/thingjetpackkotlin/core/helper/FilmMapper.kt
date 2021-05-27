package com.thing.bangkit.thingjetpackkotlin.core.helper

import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.entity.FilmEntity
import com.thing.bangkit.thingjetpackkotlin.core.data.remote.FilmResponse
import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film

object FilmMapper {

//ListMapper
    fun mapResponsesToFilms(inputResponse: ArrayList<FilmResponse>): ArrayList<Film> {
        val filmList = ArrayList<Film>()
        inputResponse.map {
            val film = Film(it.id, it.overview, it.releaseDate,it.popularity,it.voteAverage,it.title,it.voteCount,it.poster,it.myType, it.favorite)
            filmList.add(film)
        }
        return filmList
    }

    fun mapEntitiesToFilms(inputEntity: ArrayList<FilmEntity>): ArrayList<Film> {
        val filmList = ArrayList<Film>()
        inputEntity.map {
            val film = Film(it.id, it.overview, it.releaseDate,it.popularity,it.voteAverage,it.title,it.voteCount,it.poster,it.myType, it.favorite)
            filmList.add(film)
        }
        return filmList
    }

    fun mapFilmsToResponses(inputFilm: ArrayList<Film>): ArrayList<FilmResponse> {
        val filmResponseList = ArrayList<FilmResponse>()
        inputFilm.map {
            val film = FilmResponse(it.id, it.overview, it.releaseDate,it.popularity,it.voteAverage,it.title,it.voteCount,it.poster,it.myType, it.favorite)
            filmResponseList.add(film)
        }
        return filmResponseList
    }

    fun mapFilmsToEntities(inputFilm: List<Film>): List<FilmEntity> {
        val filmEntityList = ArrayList<FilmEntity>()
        inputFilm.map {
            val film = FilmEntity(it.id, it.overview, it.releaseDate,it.popularity,it.voteAverage,it.title,it.voteCount,it.poster,it.myType, it.favorite)
            filmEntityList.add(film)
        }
        return filmEntityList
    }

//ObjectMapper
    fun mapResponseToFilm(it: FilmResponse): Film {

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

    fun mapEntityToFilm(it: FilmEntity): Film {

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

    fun mapFilmToResponse(it: Film): FilmResponse {

        return FilmResponse(it.id,
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

    fun mapFilmToEntity(it: Film): FilmEntity {

        return FilmEntity(it.id,
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