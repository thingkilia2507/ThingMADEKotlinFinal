package com.thing.bangkit.thingjetpackkotlin.activity

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.thing.bangkit.thingjetpackkotlin.R
import com.thing.bangkit.thingjetpackkotlin.model.DummyFilm
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val context: Context = ApplicationProvider.getApplicationContext()
    private val movies = DummyFilm.getGenerateDummyMovies(context)
    private val tvshows = DummyFilm.getGenerateDummyTvShows(context)
    private val dummyMovie = DummyFilm.getFilmFromId(0,1)
    private val dummyTvShow = DummyFilm.getFilmFromId(0,2)

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    /* Another way
       private lateinit var context: Context
    private lateinit var movies: ArrayList<Film>
    private lateinit var tvshows : ArrayList<Film>
    private var dummyMovie : Film? = null
    private var dummyTvShow : Film? = null

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        ActivityScenario.launch(MainActivity::class.java)
        movies = DummyFilm.getGenerateDummyMovies(context)
        tvshows = DummyFilm.getGenerateDummyTvShows(context)
        dummyMovie = DummyFilm.getFilmFromId(0,1)
        dummyTvShow = DummyFilm.getFilmFromId(0,2)

    }
    * */

    @Test
    fun loadMovie() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_film)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movies.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_list_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovie?.title)))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(withText(dummyMovie?.duration)))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovie?.overview)))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText("${dummyMovie?.rating}%")))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(withText(dummyMovie?.releaseDate)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(dummyMovie?.genre)))
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_list_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_film)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvshows.size))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_list_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyTvShow?.title)))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(withText(dummyTvShow?.duration)))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyTvShow?.overview)))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText("${dummyTvShow?.rating}%")))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(withText(dummyTvShow?.releaseDate)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(dummyTvShow?.genre)))
    }

}