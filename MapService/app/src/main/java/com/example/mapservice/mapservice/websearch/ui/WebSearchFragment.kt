package com.example.mapservice.mapservice.websearch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mapservice.databinding.FragmentWebSearchBinding
import com.example.mapservice.mapservice.utils.DataState
import com.example.mapservice.mapservice.MainActivity
import com.example.mapservice.mapservice.utils.VerticalItemDecoration
import com.example.mapservice.mapservice.websearch.viewmodel.WebSearchViewModel
import com.example.mapservice.mapservice.websearch.adapter.SearchAdapter
import com.google.android.material.snackbar.Snackbar

class WebSearchFragment : Fragment() {
    private lateinit var binding: FragmentWebSearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private val viewModel: WebSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        searchVideo()
    }

    private fun searchVideo() {
        binding.buttonSearch.setOnClickListener {
            viewModel.getSearchResults(binding.edittextSearch.text.toString())
            getSearchResults()
        }
    }

    private fun initRecyclerView() {
        searchAdapter = SearchAdapter(requireContext())
        binding.recyclerviewSearchResult.adapter = searchAdapter
        binding.recyclerviewSearchResult.addItemDecoration(VerticalItemDecoration(10))
    }

    private fun getSearchResults() {
        viewModel.searchResponse.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                DataState.Status.SUCCESS -> {
                    searchAdapter.submitList(it.data?.documents)
                    binding.progressbarSearch.visibility = View.GONE
                    binding.recyclerviewSearchResult.visibility = View.VISIBLE
                }
                DataState.Status.LOADING -> {
                    binding.progressbarSearch.visibility = View.VISIBLE
                    binding.recyclerviewSearchResult.visibility = View.GONE
                }
                DataState.Status.ERROR -> {
                    binding.progressbarSearch.visibility = View.GONE
                    binding.recyclerviewSearchResult.visibility = View.VISIBLE
                    Snackbar.make(binding.root, "서버 및 입력데이터 오류입니다.", Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }
}