package com.example.rxjavasample.searchmovie.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.rxjavasample.databinding.FragmentSearchMovieBinding
import com.example.rxjavasample.searchmovie.adapter.MovieListAdapter
import com.example.rxjavasample.searchmovie.viewmodel.SearchMovieViewModel
import com.example.rxjavasample.util.VerticalItemDecoration
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchMovieFragment : Fragment() {
    private lateinit var binding: FragmentSearchMovieBinding
    private val viewModel: SearchMovieViewModel by activityViewModels()
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchMovieBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        searchMovie()
        setCountryFilter()
    }

    private fun initView() {
        movieListAdapter = MovieListAdapter {
            Log.d("item click", "item click")
        }
        binding.recyclerviewNews.adapter = movieListAdapter
        binding.recyclerviewNews.addItemDecoration(VerticalItemDecoration(10))
    }

    private fun searchMovie() {
        binding.buttonSearch.setOnClickListener {
            viewModel.getMovieList()
            viewModel.movieList.observe(viewLifecycleOwner, Observer {
                movieListAdapter.submitList(viewModel.movieList.value!!.items)
                Log.e("movie list", "movie list")
            })
        }
    }

    private fun setCountryFilter() {
        binding.textviewFilterCountry.setOnClickListener {
            MovieFilterDialogFragment().show(childFragmentManager, "FilterDialog")
        }
    }
}