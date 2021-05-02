package com.thing.bangkit.thingjetpackkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thing.bangkit.thingjetpackkotlin.activity.DetailActivity.Companion.TYPE_ID_MOVIE
import com.thing.bangkit.thingjetpackkotlin.activity.DetailActivity.Companion.TYPE_ID_TV_SHOW
import com.thing.bangkit.thingjetpackkotlin.adapter.FilmAdapter
import com.thing.bangkit.thingjetpackkotlin.databinding.ContentFragmentListBinding
import com.thing.bangkit.thingjetpackkotlin.databinding.FragmentFilmBinding
import com.thing.bangkit.thingjetpackkotlin.viemodel.FilmViewModel
import com.thing.bangkit.thingjetpackkotlin.viemodel.ViewModelFactory

class FilmFragment : Fragment() {

    private lateinit var fragmentFilmBinding: FragmentFilmBinding
    private lateinit var binding: ContentFragmentListBinding
    private lateinit var viewModel: FilmViewModel

    companion object {
        private const val ARG_POSITION_BUNDLE = "BUNDLE_POSITION"

        @JvmStatic
        fun newInstance(position: Int) =
            FilmFragment().apply {
                arguments = Bundle().apply {
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
        viewModel = ViewModelProvider(requireActivity(),
            ViewModelFactory.getInstance())[FilmViewModel::class.java]
        return fragmentFilmBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBarShow(true)
        val adapter = FilmAdapter()
        if (arguments?.getInt(ARG_POSITION_BUNDLE) == 0) {
            viewModel.moviesData().observe(this, {
                adapter.listFilm = it
                setEmptyView(it.size < 1, adapter)
            })
            adapter.type = TYPE_ID_MOVIE
        } else {
            viewModel.tvShowsData().observe(this, {
                adapter.listFilm = it
                setEmptyView(it.size < 1, adapter)
            })
            adapter.type = TYPE_ID_TV_SHOW
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

    private fun progressBarShow(show: Boolean) {
        if (show) binding.pbLoading.visibility =
            View.VISIBLE else binding.pbLoading.visibility = View.GONE
    }

    private fun ivEmptyShow(show: Boolean) {
        if (show) binding.ivCompactEmpty.visibility =
            View.VISIBLE else binding.ivCompactEmpty.visibility = View.GONE
    }
}