package com.thing.bangkit.thingjetpackkotlin.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.thing.bangkit.thingjetpackkotlin.R
import com.thing.bangkit.thingjetpackkotlin.databinding.ActivityDetailBinding
import com.thing.bangkit.thingjetpackkotlin.databinding.ContentDetailBinding
import com.thing.bangkit.thingjetpackkotlin.model.Film

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var contentBinding: ContentDetailBinding

    companion object {
        const val EXTRA_FILM = "extra_film"
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        contentBinding = binding.contentDetail
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extraFilm = intent.getParcelableExtra<Film>(EXTRA_FILM)
        if (extraFilm != null) {
            binding.toolbarLayout.setExpandedTitleColor(getColor(R.color.white))
            binding.toolbarLayout.setCollapsedTitleTextColor(getColor(R.color.white))
            binding.toolbarLayout.title = extraFilm.title.toUpperCase()
            Glide.with(applicationContext)
                .asBitmap()
                .placeholder(R.drawable.movielogo)
                .error(R.drawable.movielogo)
                .load(extraFilm.poster)
                .into(binding.ivCompactPoster)


            contentBinding.tvTitle.text = extraFilm.title
            contentBinding.tvOverview.text = extraFilm.overview
            contentBinding.tvRating.text = "${extraFilm.rating}%"
            contentBinding.progressbarCircleRate.progress = extraFilm.rating
            contentBinding.progressbarCircleRate.progressDrawable =
                if (extraFilm.rating > 69) getDrawable(R.drawable.circle) else
                    getDrawable(R.drawable.circleyellow)
            contentBinding.tvReleaseDate.text = extraFilm.releaseDate
            contentBinding.tvDuration.text = extraFilm.duration
            contentBinding.tvGenre.text = extraFilm.genre
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}