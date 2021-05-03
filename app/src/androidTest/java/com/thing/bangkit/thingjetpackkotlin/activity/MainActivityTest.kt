package com.thing.bangkit.thingjetpackkotlin.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.thing.bangkit.thingjetpackkotlin.R
import com.thing.bangkit.thingjetpackkotlin.helper.EspressoIdlingResource
import com.thing.bangkit.thingjetpackkotlin.repository.FilmRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovie() {
        val movies = FilmRepository.getInstance().getMoviesList()
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_film)).perform(movies.value?.size?.let {
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(it)
        })
    }

    @Test
    fun loadDetailMovie() {
        val movies = FilmRepository.getInstance().getMoviesList()
        val dummyMovie =
            movies.value?.get(0)?.let { FilmRepository.getInstance().getDetailFromId(it.id,1).value }
        dummyMovie?.let {
            onView(withId(R.id.rv_list_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()))
            onView(withId(R.id.swipeIt)).perform(ViewActions.swipeUp())
            onView(withId(R.id.swipeIt)).perform(ViewActions.swipeUp())
            onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_title)).check(matches(withText(dummyMovie.title)))
            onView(withId(R.id.tv_vote_count)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_vote_count)).check(matches(withText("Vote Count : ${dummyMovie.voteCount}")))
            onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovie.overview)))
            onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_rating)).check(matches(withText("${
                dummyMovie.voteAverage.times(10).toInt()
            }%")))
            onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_release_date)).check(matches(withText(dummyMovie.releaseDate)))
            onView(withId(R.id.tv_popularity)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_popularity)).check(matches(withText("Popularity : ${dummyMovie.popularity}")))
        }
    }

    @Test
    fun loadTvShows() {
        val tvShows = FilmRepository.getInstance().getTvShowsList()
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_list_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_film)).perform(tvShows.value?.size?.let {
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(it)
        })
    }

    @Test
    fun loadDetailTvShows() {
        val tvShows = FilmRepository.getInstance().getTvShowsList()
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_list_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        val dummyTvShow = tvShows.value?.get(0)?.id?.let { FilmRepository.getInstance().getDetailFromId(it, 2).value }
        dummyTvShow?.let {
            onView(withId(R.id.swipeIt)).perform(ViewActions.swipeUp())
            onView(withId(R.id.swipeIt)).perform(ViewActions.swipeUp())
            onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_title)).check(matches(withText(dummyTvShow.title)))
            onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_overview)).check(matches(withText(dummyTvShow.overview)))
            onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_rating)).check(matches(withText("${
                dummyTvShow.voteAverage.times(10).toInt()
            }%")))
            onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_release_date)).check(matches(withText(dummyTvShow.releaseDate)))
            onView(withId(R.id.tv_popularity)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_popularity)).check(matches(withText("Popularity : ${dummyTvShow.popularity}")))
            onView(withId(R.id.tv_vote_count)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_vote_count)).check(matches(withText("Vote Count : ${dummyTvShow.voteCount}")))
        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }
}