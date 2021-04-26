package com.thing.bangkit.thingjetpackkotlin.viemodel

import android.content.Context
import junit.framework.Assert.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class FilmViewModelTest{
    private lateinit var viewModel: FilmViewModel

    private lateinit var context : Context

    @Before
    fun setUp() {
//        MockitoAnnotations.openMocks(this);
        viewModel = FilmViewModel()
//        context = ApplicationProvider.getApplicationContext()
        context = Mockito.mock(Context::class.java)
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