package com.thing.bangkit.thingjetpackkotlin.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thing.bangkit.thingjetpackkotlin.core.R
import com.thing.bangkit.thingjetpackkotlin.core.databinding.CardItemListFilmBinding
import com.thing.bangkit.thingjetpackkotlin.core.domain.model.Film
import com.thing.bangkit.thingjetpackkotlin.core.helper.Utility.IMAGE_URL

class FilmFavAdapter(private val onIntentDetail: IGotoDetailFav)  : PagedListAdapter<Film, FilmFavAdapter.FilmViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem == newItem
            }
        }
    }

    var type : Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(CardItemListFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, type, onIntentDetail)
        }
    }

    class FilmViewHolder(private val binding: CardItemListFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
        fun bind(film: Film, type: Int, onIntentDetail: IGotoDetailFav) {
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

            itemView.setOnClickListener{goToDetail(film.id, type, binding.ivCompactPosterItem, onIntentDetail)}
            binding.btnDetail.setOnClickListener {
                goToDetail(film.id, type, binding.ivCompactPosterItem, onIntentDetail)
            }

        }



        private fun goToDetail(
            id: Int,
            type: Int,
            iv: AppCompatImageView,
            onIntentDetail: IGotoDetailFav
        ) {
            onIntentDetail.onIntent(id,type, iv)
        }

    }

    interface IGotoDetailFav{
        fun onIntent(id: Int, type: Int, iv: AppCompatImageView)
    }
}