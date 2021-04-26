package com.thing.bangkit.thingjetpackkotlin.viemodel

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import junit.framework.Assert.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class FilmViewModelTest{
    private lateinit var viewModel: FilmViewModel

    private lateinit var context : Context

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this);
        viewModel = FilmViewModel()
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun getGenerateMoviesData() {
        val movies = viewModel.getGenerateMoviesData(context)
        assertNotNull(movies)
        assertEquals(12, movies.size)
    }
    @Test
    fun getGenerateTvShowData() {
        val tvshows = viewModel.getGenerateTvShowData(context)
        assertNotNull(context)
        assertEquals(12, tvshows.size)
    }
}