package com.example.rxjavasample.searchmovie.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.rxjavasample.databinding.FragmentSearchMovieBinding
import com.example.rxjavasample.searchmovie.adapter.MovieListAdapter
import com.example.rxjavasample.searchmovie.adapter.SearchHistoryAdapter
import com.example.rxjavasample.searchmovie.remote.model.MovieResponseModel
import com.example.rxjavasample.searchmovie.viewmodel.SearchMovieViewModel
import com.example.rxjavasample.util.VerticalItemDecoration
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchMovieFragment : Fragment() {
    private lateinit var binding: FragmentSearchMovieBinding
    private val viewModel: SearchMovieViewModel by activityViewModels()
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var searchHistoryAdapter: SearchHistoryAdapter

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
        loadSearchHistory()
    }

    private fun initView() {
        searchHistoryAdapter = SearchHistoryAdapter()
        movieListAdapter = MovieListAdapter { item ->
            openMovieUrl(item)
        }

        binding.recyclerviewNews.adapter = movieListAdapter
        binding.recyclerviewNews.addItemDecoration(VerticalItemDecoration(10))
        binding.recyclerviewSearchHistory.adapter = searchHistoryAdapter
        binding.recyclerviewSearchHistory.addItemDecoration(VerticalItemDecoration(5))
    }

    private fun searchMovie() {
        binding.buttonSearch.setOnClickListener {
            viewModel.getMovieList()
            viewModel.movieList.observe(viewLifecycleOwner, Observer {
                movieListAdapter.submitList(viewModel.movieList.value!!.items)
            })
            binding.edittextSearchNews.clearFocus()
        }
    }

    private fun setCountryFilter() {
        binding.textviewFilterCountry.setOnClickListener {
            MovieFilterDialogFragment().show(childFragmentManager, "FilterDialog")
        }
    }

    private fun openMovieUrl(item: MovieResponseModel.Item) {
        requireActivity().run {
            val url = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            startActivity(url, null)
        }
    }

    private fun loadSearchHistory() {
        viewModel.queryList.observe(viewLifecycleOwner, Observer {
            if (!viewModel.queryList.value.isNullOrEmpty()) {
                detectFocus()
            }
        })
    }

    private fun  detectFocus() {
        binding.edittextSearchNews.setOnFocusChangeListener{view, hasFocus ->
            if (hasFocus) {
                binding.recyclerviewSearchHistory.visibility = View.VISIBLE
                searchHistoryAdapter.submitList(viewModel.queryList.value!!)
            } else {
                binding.recyclerviewSearchHistory.visibility = View.GONE
            }
        }
    }
}