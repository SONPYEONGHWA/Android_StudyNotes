package com.example.recyclerviewdatabinding.search.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewdatabinding.utils.VerticalItemDecoration
import com.example.recyclerviewdatabinding.databinding.FragmentSearchBinding
import com.example.recyclerviewdatabinding.search.adapter.SearchAdapter
import com.example.recyclerviewdatabinding.search.model.SearchResponse
import com.example.recyclerviewdatabinding.search.viewmodel.SearchViewModel


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: SearchAdapter
    private var docsList = mutableListOf<SearchResponse.Documents>()
//    private val viewModel: SearchViewModel by viewModels()
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        initRecyclerView(view)
        searchDocs()
    }

    fun initRecyclerView(view: View) {
        adapter = SearchAdapter(view.context)
        binding.recyclerviewSearch.adapter = adapter
        binding.recyclerviewSearch.addItemDecoration(VerticalItemDecoration(10))

    }

    fun searchDocs() {
        binding.buttonSearch.setOnClickListener {
            viewModel.searchDocs(binding.edittextSearch.text.toString())

            viewModel.docsList.observe(viewLifecycleOwner, Observer {
                adapter.datas = it
                adapter.notifyDataSetChanged()
            })
        }
    }
}