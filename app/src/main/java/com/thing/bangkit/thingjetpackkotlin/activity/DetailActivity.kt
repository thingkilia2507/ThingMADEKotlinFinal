package com.thing.bangkit.thingjetpackkotlin.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.thing.bangkit.thingjetpackkotlin.R
import com.thing.bangkit.thingjetpackkotlin.databinding.ActivityDetailBinding
import com.thing.bangkit.thingjetpackkotlin.databinding.ContentDetailBinding
import com.thing.bangkit.thingjetpackkotlin.model.Film
import com.thing.bangkit.thingjetpackkotlin.viemodel.FilmViewModel
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var contentBinding: ContentDetailBinding
    private val viewModel: FilmViewModel by viewModels()

    companion object {
        const val EXTRA_FILM_ID = "extra_film_id"
        const val EXTRA_FILM_TYPE = "extra_film_type"
        const val TYPE_ID_MOVIE = 1
        const val TYPE_ID_TVSHOW = 2
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        contentBinding = binding.contentDetail
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val filmId = intent.getIntExtra(EXTRA_FILM_ID, -1)
        val type = intent.getIntExtra(EXTRA_FILM_TYPE, -1)

        val film = viewModel.getFilmsFromId(filmId, type)
        if(film!=null)  bind(film)

    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    private fun bind(film: Film) {
        binding.toolbarLayout.setExpandedTitleColor(getColor(R.color.white))
        binding.toolbarLayout.setCollapsedTitleTextColor(getColor(R.color.white))
        binding.toolbarLayout.title = film.title.toUpperCase(Locale.getDefault())
        Glide.with(applicationContext)
            .asBitmap()
            .placeholder(R.drawable.movielogo)
            .error(R.drawable.movielogo)
            .load(film.poster)
            .into(binding.ivCompactPoster)


        contentBinding.tvTitle.text = film.title
        contentBinding.tvOverview.text = film.overview
        contentBinding.tvRating.text = "${film.rating}%"
        contentBinding.progressbarCircleRate.progress = film.rating
        contentBinding.progressbarCircleRate.progressDrawable =
            if (film.rating > 69) getDrawable(R.drawable.circle) else
                getDrawable(R.drawable.circleyellow)
        contentBinding.tvReleaseDate.text = film.releaseDate
        contentBinding.tvDuration.text = film.duration
        contentBinding.tvGenre.text = film.genre
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}