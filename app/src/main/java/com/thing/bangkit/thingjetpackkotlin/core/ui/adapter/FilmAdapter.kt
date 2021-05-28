package com.thing.bangkit.thingjetpackkotlin.core.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thing.bangkit.thingjetpackkotlin.R
import com.thing.bangkit.thingjetpackkotlin.activity.DetailActivity
import com.thing.bangkit.thingjetpackkotlin.activity.DetailActivity.Companion.EXTRA_FILM_ID
import com.thing.bangkit.thingjetpackkotlin.activity.DetailActivity.Companion.EXTRA_FILM_TYPE
import com.thing.bangkit.thingjetpackkotlin.databinding.CardItemListFilmBinding
import com.thing.bangkit.thingjetpackkotlin.core.helper.Utility.IMAGE_URL
import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    var listFilm: ArrayList<Film> = ArrayList()
    var type : Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(CardItemListFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(listFilm[position], type)
    }

    override fun getItemCount(): Int = listFilm.size

    class FilmViewHolder(private val binding: CardItemListFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
        fun bind(film: Film, type: Int) {
            binding.tvTitleItem.text = film.title
            binding.tvOverviewItem.text = film.overview

            val rating = (film.voteAverage * 10).toInt()
            binding.tvRatingItem.text = "$rating%"
            binding.progressbarCircleRateItem.progress = rating
            binding.progressbarCircleRateItem.progressDrawable =
                if (rating > 69) itemView.context.getDrawable(R.drawable.circle) else
                    itemView.context.getDrawable(R.drawable.circleyellow)
            binding.tvReleaseDateItem.text = film.releaseDate
            Glide.with(itemView.context).asBitmap()
                .placeholder(R.drawable.movielogo)
                .error(R.drawable.movielogo)
                .load(IMAGE_URL + film.poster)
                .into(binding.ivCompactPosterItem)

            itemView.setOnClickListener{goToDetail(film.id, type)}
            binding.btnDetail.setOnClickListener {
                goToDetail(film.id, type)
            }

        }

        private fun goToDetail(id: Int, type: Int) {
            val i = Intent(binding.root.context, DetailActivity::class.java)
            i.putExtra(EXTRA_FILM_ID, id)
            i.putExtra(EXTRA_FILM_TYPE, type)
            val option =
                ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity,
                    binding.ivCompactPosterItem,
                    itemView.context.getString(R.string.transition_poster))
            itemView.context.startActivity(i, option.toBundle())
        }


    }
}