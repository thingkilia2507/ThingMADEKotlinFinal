package com.thing.bangkit.thingjetpackkotlin.viemodel

import android.content.Context
import android.os.Build
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import androidx.test.core.app.ApplicationProvider
import com.thing.bangkit.thingjetpackkotlin.model.DummyFilm
import com.thing.bangkit.thingjetpackkotlin.model.Film
import com.thing.bangkit.thingjetpackkotlin.repository.LocalDataFavRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import java.util.concurrent.Executors


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
@LooperMode(LooperMode.Mode.PAUSED)
class FilmFavViewModelTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private lateinit var viewModelFav: FilmFavViewModel


    @Mock
    private lateinit var localFilmFavRepository: LocalDataFavRepository

    @Mock
    private lateinit var observer: Observer<PagedList<Film>>


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModelFav = FilmFavViewModel(localFilmFavRepository)
    }


    @Test
    fun favMoviesData() {
        val expectMovies = MutableLiveData<PagedList<Film>>()
        expectMovies.value =  PagedTestDataSources.snapshot(DummyFilm.getGenerateDummyMovies(context))

        `when`(localFilmFavRepository.getAllFavMovie()).thenReturn(expectMovies)

        Shadows.shadowOf(Looper.getMainLooper()).idle()
        viewModelFav.favMoviesData().observeForever(observer)
        verify(observer).onChanged(expectMovies.value)

        val expectValueMovies = expectMovies.value
        val actualValues = viewModelFav.favMoviesData().value

        assertEquals(expectValueMovies, actualValues)
        assertEquals(expectValueMovies?.snapshot(), actualValues?.snapshot())
        assertEquals(expectValueMovies?.size, actualValues?.size)
    }

    @Test
    fun favTvShowsData() {

        val expectTvShows = MutableLiveData<PagedList<Film>>()
        expectTvShows.value = PagedTestDataSources.snapshot(DummyFilm.getGenerateDummyTvShows(context))

        `when`(localFilmFavRepository.getAllFavTvShow()).thenReturn(expectTvShows)

        viewModelFav.favTvShowsData().observeForever(observer)
        verify(observer).onChanged(expectTvShows.value)

        val expectValueTvShows = expectTvShows.value
        val actualValues = viewModelFav.favTvShowsData().value

        assertEquals(expectValueTvShows, actualValues)
        assertEquals(expectValueTvShows?.snapshot(), actualValues?.snapshot())
        assertEquals(expectValueTvShows?.size, actualValues?.size)
    }

    @Test
    fun getFavFilmFromId() {
        val dummyFilm = DummyFilm.getGenerateDummyMovies(context)[0]
        val filmId = dummyFilm.id
        `when`(localFilmFavRepository.getFavFilmById(filmId)).thenReturn(dummyFilm)
        val actualValue = viewModelFav.getFavFilmFromId(filmId)
        assertEquals(dummyFilm, actualValue)
    }

    @Test
    fun insertFavFilmData() {
        val dummy = MutableLiveData<PagedList<Film>>()
        dummy.value = PagedTestDataSources.snapshot(DummyFilm.getGenerateDummyTvShows(context))
        `when`(localFilmFavRepository.getAllFavTvShow()).thenReturn(dummy)

        val dummyFilm = DummyFilm.getGenerateDummyTvShows(context)[0]

        Shadows.shadowOf(Looper.getMainLooper()).idle()
        `when`(localFilmFavRepository.insertFavFilm(dummyFilm)).then { DummyFilm.insertTvShow(dummyFilm)}

        //initalSize of the list
        val initialSizeFavFilm = viewModelFav.favTvShowsData().value?.size

        //insert film
        viewModelFav.insertFavFilmData(dummyFilm)

        //we add 1 film so the size must be +1
        val expectedSize = initialSizeFavFilm?.plus(1)

        // try to get the new size of the list
        dummy.value = PagedTestDataSources.snapshot(DummyFilm.getGenerateDummyTvShows(context))
        val actualSize = viewModelFav.favTvShowsData().value?.size

        // the new size must equals to expected size
        assertEquals(expectedSize, actualSize)

        //the last position value must be the new film we added
        assertEquals(viewModelFav.favTvShowsData().value?.last(), dummyFilm)
    }

    @Test
    fun deleteFavFilmFromId() {
        val dummy = MutableLiveData<PagedList<Film>>()
        dummy.value = PagedTestDataSources.snapshot(DummyFilm.getGenerateDummyTvShows(context))
        `when`(localFilmFavRepository.getAllFavTvShow()).thenReturn(dummy)

        //you can change the id : 0-11 (based on the list size)
        val id = 1
        `when`(localFilmFavRepository.deleteById(id)).thenReturn(DummyFilm.deleteTvShow(id))

        //initalSize of the list = 12
        val initialSizeFavFilm = viewModelFav.favTvShowsData().value?.size

        //delete it
        viewModelFav.deleteFavFilmFromId(id)

        //we delete 1 film so the size must be -1
        val expectedSize = initialSizeFavFilm?.minus(1)

        // try to get the new size of the list
        dummy.value = PagedTestDataSources.snapshot(DummyFilm.getGenerateDummyTvShows(context))
        val actualSize = viewModelFav.favTvShowsData().value?.size

        // the new size must equals to expected size
        assertEquals(expectedSize, actualSize)

        //film with the deleted id, the position of it must be already replace by the next Film
        assertEquals(viewModelFav.favTvShowsData().value?.get(id)?.id, id+1)
    }

    class PagedTestDataSources private constructor(private val items: List<Film>) : PositionalDataSource<Film>() {
        companion object {
            fun snapshot(items: List<Film> = listOf()): PagedList<Film> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Film>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Film>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}
