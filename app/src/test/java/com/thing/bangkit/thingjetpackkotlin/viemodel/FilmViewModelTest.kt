package com.thing.bangkit.thingjetpackkotlin.viemodel

import android.content.Context
import android.os.Build
import android.os.Looper
import androidx.test.core.app.ApplicationProvider
import com.thing.bangkit.thingjetpackkotlin.model.DummyFilm
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode


//@RunWith(AndroidJUnit4ClassRunner::class) // if it's error, write the import by yourself on top : import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
@LooperMode(LooperMode.Mode.PAUSED)
class FilmViewModelTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
//    private lateinit var context: Context
    private lateinit var viewModel: FilmViewModel

    @Before
    fun setUp() {
//        context = InstrumentationRegistry.getInstrumentation().targetContext
        viewModel = FilmViewModel()
    }

    @Test
    fun getGenerateMoviesData() {
        val movies = viewModel.getGenerateTvShowData(context)
        assertNotNull(movies)
        assertEquals(12, movies.size)
    }

    @Test
    fun getGenerateTvShowData() {
        val tvshows = viewModel.getGenerateTvShowData(context)
        assertNotNull(tvshows)
        assertEquals(12, tvshows.size)
    }

    @Test
    fun getFilmsFromId() {
        viewModel.getGenerateMoviesData(context)
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val dummyFilm = DummyFilm.getFilmFromId(0, 1)
        assertNotNull(dummyFilm)

        val film = viewModel.getFilmsFromId(0, 1)
        assertNotNull(film)
        assertEquals(film, dummyFilm)
    }

}