package com.thing.bangkit.thingjetpackkotlin.activity

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.thing.bangkit.thingjetpackkotlin.R
import com.thing.bangkit.thingjetpackkotlin.databinding.ActivityDetailBinding
import com.thing.bangkit.thingjetpackkotlin.databinding.ContentDetailBinding
import com.thing.bangkit.thingjetpackkotlin.helper.Utility.IMAGE_URL
import com.thing.bangkit.thingjetpackkotlin.model.Film
import com.thing.bangkit.thingjetpackkotlin.viemodel.FilmViewModel
import com.thing.bangkit.thingjetpackkotlin.viemodel.ViewModelFactory
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var contentBinding: ContentDetailBinding
    private lateinit var viewModel: FilmViewModel

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

        binding.pbLoading.visibility = ProgressBar.VISIBLE
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val filmId = intent.getIntExtra(EXTRA_FILM_ID, -1)
        val type = intent.getIntExtra(EXTRA_FILM_TYPE, -1)

        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[FilmViewModel::class.java]
        viewModel.getFilmsFromId(filmId, type).observe(this, {
            bind(it)
            binding.pbLoading.visibility = ProgressBar.GONE
        })

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
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}