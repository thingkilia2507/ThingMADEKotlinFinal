package com.thing.bangkit.thingjetpackkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.thing.bangkit.thingjetpackkotlin.activity.DetailActivity.Companion.TYPE_ID_MOVIE
import com.thing.bangkit.thingjetpackkotlin.activity.DetailActivity.Companion.TYPE_ID_TV_SHOW
import com.thing.bangkit.thingjetpackkotlin.core.helper.EspressoIdlingResource
import com.thing.bangkit.thingjetpackkotlin.core.ui.adapter.FilmAdapter
import com.thing.bangkit.thingjetpackkotlin.core.ui.adapter.FilmFavAdapter
import com.thing.bangkit.thingjetpackkotlin.core.ui.factory.ViewModelFactory
import com.thing.bangkit.thingjetpackkotlin.databinding.ContentFragmentListBinding
import com.thing.bangkit.thingjetpackkotlin.databinding.FragmentFilmBinding
import com.thing.bangkit.thingjetpackkotlin.viemodel.FilmFavViewModel
import com.thing.bangkit.thingjetpackkotlin.viemodel.FilmViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmFragment : Fragment() {

    private lateinit var fragmentFilmBinding: FragmentFilmBinding
    private lateinit var binding: ContentFragmentListBinding
    private lateinit var viewModel: FilmViewModel
    private lateinit var viewModelFav: FilmFavViewModel

    companion object {
        private const val ARG_POSITION_BUNDLE = "BUNDLE_POSITION"
        private const val ARG_PAGE_TYPE = "TYPE_PAGE_IS_FAVORITE"

        @JvmStatic
        fun newInstance(position: Int, pageType: Int) =
            FilmFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PAGE_TYPE, pageType)
                    putInt(ARG_POSITION_BUNDLE, position)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentFilmBinding = FragmentFilmBinding.inflate(inflater, container, false)
        binding = fragmentFilmBinding.contentFragmentList
        viewModel = ViewModelProvider(requireActivity(), ViewModelFactory.getInstance(requireActivity()))[FilmViewModel::class.java]
        viewModelFav = ViewModelProvider(requireActivity(), ViewModelFactory.getInstance(requireActivity()))[FilmFavViewModel::class.java]
        return fragmentFilmBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()

    }

    override fun onResume() {
        super.onResume()
        if(arguments?.getInt(ARG_PAGE_TYPE) == 1)
            binding.rvListFilm.adapter?.itemCount?.let {
                ivEmptyShow(it < 1)
            }
    }

    private fun loadData() {
        progressBarShow(true)
        EspressoIdlingResource.increment()
        if (arguments?.getInt(ARG_POSITION_BUNDLE) == 0) {
            if (arguments?.getInt(ARG_PAGE_TYPE) == 1) {
                val adapter = FilmFavAdapter()
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
                val adapter = FilmAdapter()
                adapter.type = TYPE_ID_MOVIE
                viewModel.moviesData().observe(viewLifecycleOwner, {
                    adapter.listFilm = it
                    setEmptyView(it.size < 1, adapter)

                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                })
            }
        } else {
            if (arguments?.getInt(ARG_PAGE_TYPE) == 1) {
                val adapter = FilmFavAdapter()
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
            } else {
                val adapter = FilmAdapter()
                adapter.type = TYPE_ID_TV_SHOW
                viewModel.tvShowsData().observe(viewLifecycleOwner, {
                    adapter.listFilm = it
                    setEmptyView(it.size < 1, adapter)

                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                })
            }
        }
    }

    private fun setEmptyView(isEmpty: Boolean, adapter: FilmAdapter) {
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
        if (show) binding.ivCompactEmpty.visibility =
            View.VISIBLE else binding.ivCompactEmpty.visibility = View.GONE
    }
}