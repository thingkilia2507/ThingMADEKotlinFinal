package com.thing.bangkit.thingjetpackkotlin.viemodel

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class FilmViewModelTest : TestCase() {

    private lateinit var viewModel: FilmViewModel

    @Before
    override fun setUp() {
        viewModel = FilmViewModel()
    }

    @Test
    fun testGetGenerateMoviesData() {}

    @Test
    fun testGetGenerateTvShowData() {}
}