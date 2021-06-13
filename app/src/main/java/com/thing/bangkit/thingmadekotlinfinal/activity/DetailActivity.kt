package com.thing.bangkit.thingmadekotlinfinal.activity

import android.content.Intent
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.thing.bangkit.thingmadekotlinfinal.R
import com.thing.bangkit.thingmadekotlinfinal.core.domain.model.Film
import com.thing.bangkit.thingmadekotlinfinal.core.helper.Utility
import com.thing.bangkit.thingmadekotlinfinal.core.helper.Utility.IMAGE_URL
import com.thing.bangkit.thingmadekotlinfinal.databinding.ActivityDetailBinding
import com.thing.bangkit.thingmadekotlinfinal.databinding.ContentDetailBinding
import com.thing.bangkit.thingmadekotlinfinal.databinding.NetworkLostViewBinding
import com.thing.bangkit.thingmadekotlinfinal.helper.EspressoIdlingResource
import com.thing.bangkit.thingmadekotlinfinal.viemodel.DetailViewModel
import com.thing.bangkit.thingmadekotlinfinal.viemodel.FilmViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var contentBinding: ContentDetailBinding
    private val viewModel: FilmViewModel by viewModel()
    private val viewModelFavViewModel: DetailViewModel by viewModel()
    private var type: Int = -1
    private var filmId: Int = -1


    private lateinit var networkLostBinding: NetworkLostViewBinding
    private var checkNetworkCapabilities: NetworkCapabilities? = null

    companion object {
        const val EXTRA_FILM_ID = "extra_film_id"
        const val EXTRA_FILM_TYPE = "extra_film_type"
        const val TYPE_ID_MOVIE = 1
        const val TYPE_ID_TV_SHOW = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        contentBinding = binding.contentDetail
        setContentView(binding.root)

        filmId = intent.getIntExtra(EXTRA_FILM_ID, -1)
        type = intent.getIntExtra(EXTRA_FILM_TYPE, -1)
        checkNetworkCapabilities = Utility.checkNetworkConnection(this)
        if (checkNetworkCapabilities == null) {
            networkLostBinding = NetworkLostViewBinding.inflate(layoutInflater)
            setContentView(networkLostBinding.root)
            networkLostBinding.btnReload.setOnClickListener {
                checkNetworkCapabilities = Utility.checkNetworkConnection(this)
                if (checkNetworkCapabilities != null) {
                    val i = Intent(this, DetailActivity::class.java)
                    i.putExtra(EXTRA_FILM_ID, filmId)
                    i.putExtra(EXTRA_FILM_TYPE, type)
                    startActivity(i)
                    finish()
                }
            }
        } else {
            runDetAct()
        }


    }

    private fun runDetAct() {
        binding.pbLoading.visibility = ProgressBar.VISIBLE
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        EspressoIdlingResource.increment()
        lifecycleScope.launch {
            viewModel.getFilmsFromId(filmId, type).observe(this@DetailActivity, { film ->
                bind(film)
            })
        }

    }

    private fun checkingFavorite(film: Film) {
        lifecycleScope.launch(Dispatchers.IO) {
            val isFavorite = viewModelFavViewModel.getFavFilmFromId(film.id) != null
            withContext(Dispatchers.Main) {
                film.favorite = isFavorite
                if (isFavorite) {
                    contentBinding.ivFav.setImageResource(R.drawable.ic_favorite_yes)
                } else contentBinding.ivFav.setImageResource(R.drawable.ic_favorite_no)

                favoriteListener(film)

                binding.pbLoading.visibility = ProgressBar.GONE
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        }

    }


    private suspend fun favoriteListener(film: Film) = Dispatchers.Main {
        contentBinding.ivFav.setOnClickListener {
            if (film.favorite) {
                contentBinding.ivFav.setImageResource(R.drawable.ic_favorite_no)
                film.favorite = false
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModelFavViewModel.deleteFavFilmFromId(film.id)
                }
            } else {
                contentBinding.ivFav.setImageResource(R.drawable.ic_favorite_yes)
                film.favorite = true
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModelFavViewModel.insertFavFilmData(film)
                }
            }
        }
    }

    private fun bind(film: Film) {
        binding.toolbarLayout.setExpandedTitleColor(getColor(R.color.white))
        binding.toolbarLayout.setCollapsedTitleTextColor(getColor(R.color.white))
        binding.toolbarLayout.title = film.title.toUpperCase(Locale.getDefault())
        Glide.with(applicationContext)
            .asBitmap()
            .placeholder(R.drawable.movielogo)
            .error(R.drawable.movielogo)
            .load(IMAGE_URL + film.poster)
            .into(binding.ivCompactPoster)

        contentBinding.tvTitle.text = film.title
        contentBinding.tvOverview.text = film.overview
        val rating = (film.voteAverage * 10).toInt()
        contentBinding.tvRating.text = StringBuilder("$rating%")
        contentBinding.progressbarCircleRate.progress = rating
        contentBinding.progressbarCircleRate.progressDrawable =
            if (rating > 69) ContextCompat.getDrawable(this, R.drawable.circle) else
                ContextCompat.getDrawable(this, R.drawable.circleyellow)
        contentBinding.tvReleaseDate.text = film.releaseDate
        contentBinding.tvVoteCount.text = StringBuilder("Vote Count : ${film.voteCount}")
        contentBinding.tvPopularity.text = StringBuilder("Popularity : ${film.popularity}")

        film.myType = type
        checkingFavorite(film)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        if (isTaskRoot) {
            finishAfterTransition()
        }
        super.onDestroy()

    }

    override fun onBackPressed() {
        if (isTaskRoot) {
            finishAfterTransition()
        }
        super.onBackPressed()
    }
}