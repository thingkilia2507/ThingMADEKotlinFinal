package com.thing.bangkit.thingmadekotlinfinal.favorites.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.thing.bangkit.thingmadekotlinfinal.activity.DetailActivity
import com.thing.bangkit.thingmadekotlinfinal.activity.DetailActivity.Companion.EXTRA_FILM_ID
import com.thing.bangkit.thingmadekotlinfinal.activity.DetailActivity.Companion.EXTRA_FILM_TYPE
import com.thing.bangkit.thingmadekotlinfinal.activity.DetailActivity.Companion.TYPE_ID_MOVIE
import com.thing.bangkit.thingmadekotlinfinal.activity.DetailActivity.Companion.TYPE_ID_TV_SHOW
import com.thing.bangkit.thingmadekotlinfinal.core.adapter.FilmFavAdapter
import com.thing.bangkit.thingmadekotlinfinal.core.databinding.ContentFragmentListBinding
import com.thing.bangkit.thingmadekotlinfinal.favorites.databinding.FragmentFilmFavBinding
import com.thing.bangkit.thingmadekotlinfinal.favorites.viemodel.FilmFavViewModel
import com.thing.bangkit.thingmadekotlinfinal.favorites.helper.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel

class FilmFavFragment : Fragment() {

    private lateinit var fragmentFilmFavBinding: FragmentFilmFavBinding
    private lateinit var binding: ContentFragmentListBinding

    private val viewModelFav: FilmFavViewModel by viewModel()

    companion object {
        private const val ARG_POSITION_BUNDLE = "BUNDLE_POSITION"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentFilmFavBinding = FragmentFilmFavBinding.inflate(inflater, container, false)
        binding = fragmentFilmFavBinding.contentFragmentFavList
        return fragmentFilmFavBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()

    }

    override fun onResume() {
        super.onResume()
        loadData()
        binding.rvListFilm.adapter?.itemCount?.let {
            ivEmptyShow(it < 1)
        }
    }

    private fun loadData() {
        progressBarShow(true)
        EspressoIdlingResource.increment()
        if (arguments?.getInt(ARG_POSITION_BUNDLE) == 0) {
            val adapter = FilmFavAdapter(object : FilmFavAdapter.IGotoDetailFav {
                override fun onIntent(id: Int, type: Int, iv: AppCompatImageView) {
                    val i = Intent(binding.root.context, DetailActivity::class.java)
                    i.putExtra(EXTRA_FILM_ID, id)
                    i.putExtra(EXTRA_FILM_TYPE, type)
                    val option =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(),
                            iv,
                            requireContext().getString(com.thing.bangkit.thingmadekotlinfinal.core.R.string.transition_poster))
                    requireContext().startActivity(i, option.toBundle())
                }

            })
            adapter.type = TYPE_ID_MOVIE
            lifecycleScope.launch(Dispatchers.IO) {
                val data = viewModelFav.favMoviesData()
                withContext(Dispatchers.Main) {
                    data.observe(requireActivity(), {
                        adapter.submitList(it)
                        setEmptyView(adapter.itemCount < 1, adapter)
                    })
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
            }

        } else {
            val adapter = FilmFavAdapter(object : FilmFavAdapter.IGotoDetailFav {
                override fun onIntent(id: Int, type: Int, iv: AppCompatImageView) {
                    val i = Intent(binding.root.context, DetailActivity::class.java)
                    i.putExtra(EXTRA_FILM_ID, id)
                    i.putExtra(EXTRA_FILM_TYPE, type)
                    val option =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(),
                            iv,
                            requireContext().getString(com.thing.bangkit.thingmadekotlinfinal.core.R.string.transition_poster))
                    requireContext().startActivity(i, option.toBundle())
                }

            })
            adapter.type = TYPE_ID_TV_SHOW
            lifecycleScope.launch(Dispatchers.IO) {
                val data = viewModelFav.favTvShowsData()
                withContext(Dispatchers.Main) {
                    data.observe(requireActivity(), {
                        adapter.submitList(it)
                        setEmptyView(adapter.itemCount < 1, adapter)
                    })

                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
            }

        }
    }


    private fun setEmptyView(isEmpty: Boolean, adapter: FilmFavAdapter) {
        if (isEmpty)
            ivEmptyShow(true)
        else {
            binding.rvListFilm.apply {
                this.adapter = adapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                visibility = View.VISIBLE
                ivEmptyShow(false)
            }
        }
        progressBarShow(false)
    }

    private fun progressBarShow(show: Boolean) {
        if (show) binding.pbLoading.visibility =
            View.VISIBLE else binding.pbLoading.visibility = View.GONE
    }

    private fun ivEmptyShow(show: Boolean) {
        if (show) {
            binding.ivCompactEmpty.visibility =
                View.VISIBLE

            binding.rvListFilm.visibility = View.GONE
        } else {
            binding.ivCompactEmpty.visibility = View.GONE

        }
    }
}