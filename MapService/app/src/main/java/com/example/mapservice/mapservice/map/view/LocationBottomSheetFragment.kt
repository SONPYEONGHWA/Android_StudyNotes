package com.example.mapservice.mapservice.map.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mapservice.databinding.FragmentLocationBottomSheetBinding
import com.example.mapservice.mapservice.map.viewmodel.MapViewModel
import com.example.mapservice.mapservice.map.adapter.LocationBottomSheetAdapter
import com.example.mapservice.mapservice.map.model.LocationSearchResponse
import com.example.mapservice.mapservice.map.model.LocationSelectedModel
import com.example.mapservice.mapservice.utils.VerticalItemDecoration
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LocationBottomSheetFragment() : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLocationBottomSheetBinding
    private lateinit var adapter: LocationBottomSheetAdapter
    private val viewModel: MapViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateSearchResult()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = LocationBottomSheetAdapter {
            selectLocation(it)
            dismiss()
        }
        binding.recyclerviewLocation.adapter = adapter
        binding.recyclerviewLocation.addItemDecoration(VerticalItemDecoration(10))
    }

    private fun updateSearchResult() {
        viewModel.resultList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun selectLocation(document: LocationSearchResponse.Document) {
        viewModel.changeLocationSelected(
            LocationSelectedModel(
                document.placeName,
                document.phone,
                document.latitude.toDouble(),
                document.longtitude.toDouble()
            )
        )
    }
}