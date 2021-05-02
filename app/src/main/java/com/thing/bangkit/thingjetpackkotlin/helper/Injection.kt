package com.thing.bangkit.thingjetpackkotlin.helper

object Injection {
    fun provideRepository(): FilmRepository {
        return FilmRepository.getInstance()
    }

}