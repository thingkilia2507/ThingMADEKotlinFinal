package com.thing.bangkit.thingjetpackkotlin.viemodel

import android.content.Context
import android.os.Build
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.thing.bangkit.thingjetpackkotlin.helper.FilmRepository
import com.thing.bangkit.thingjetpackkotlin.model.DummyFilm
import com.thing.bangkit.thingjetpackkotlin.model.Film
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@RunWith(RobolectricTestRunner::class,)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
@LooperMode(LooperMode.Mode.PAUSED)
class FilmViewModelTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private lateinit var viewModel: FilmViewModel
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var listObserver: Observer<ArrayList<Film>>
    @Mock
    private lateinit var observer: Observer<Film>


    @Before
    fun setUp() {
        filmRepository = Mockito.mock(FilmRepository::class.java)
        MockitoAnnotations.openMocks(this)
        viewModel = FilmViewModel(filmRepository)
    }

    @Test
    fun getGenerateMoviesData() {
        val dummyMovie = DummyFilm.getGenerateDummyMovies(context)
        val movies = MutableLiveData<ArrayList<Film>>()
        movies.value = dummyMovie

        `when`(filmRepository.getMoviesList()).thenReturn(movies)
        val moviesEntities = viewModel.moviesData().value
        verify(filmRepository).getMoviesList()
        assertNotNull(moviesEntities)
        assertEquals(12, moviesEntities?.size)

        Shadows.shadowOf(Looper.getMainLooper()).idle()
        viewModel.moviesData().observeForever(listObserver)
        verify(listObserver).onChanged(dummyMovie)
    }

    @Test
    fun getGenerateTvShowData() {
        val dummyTvShow = DummyFilm.getGenerateDummyTvShows(context)
        val tvShows = MutableLiveData<ArrayList<Film>>()
        tvShows.value = dummyTvShow

        `when`(filmRepository.getTvShowsList()).thenReturn(tvShows)
        val tvShowsEntities = viewModel.tvShowsData().value
        verify(filmRepository).getTvShowsList()
        assertNotNull(tvShowsEntities)
        assertEquals(12, tvShowsEntities?.size)

        Shadows.shadowOf(Looper.getMainLooper()).idle()
        viewModel.tvShowsData().observeForever(listObserver)
        verify(listObserver).onChanged(dummyTvShow)

    }

    @Test
    fun getFilmsFromId() {
        DummyFilm.getGenerateDummyMovies(context)
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val dummyFilm = DummyFilm.getFilmFromId(0,1)
        assertNotNull(dummyFilm)

        val films = MutableLiveData<Film>()
        films.value = dummyFilm

        `when`(filmRepository.getDetailFromId(0,1)).thenReturn(films)
        val filmEntities = viewModel.getFilmsFromId(0, 1).value
        verify(filmRepository).getDetailFromId(0,1)
        assertNotNull(filmEntities)

        Shadows.shadowOf(Looper.getMainLooper()).idle()
        viewModel.getFilmsFromId(0,1).observeForever(observer)
        verify(observer).onChanged(dummyFilm)
    }

}