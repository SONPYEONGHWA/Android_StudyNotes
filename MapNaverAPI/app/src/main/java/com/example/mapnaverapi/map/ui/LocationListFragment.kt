package com.example.mapnaverapi.map.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.mapnaverapi.R
import com.example.mapnaverapi.databinding.FragmentLocationListBinding
import com.example.mapnaverapi.map.adapter.LocationListAdapter
import com.example.mapnaverapi.map.model.AddressModel
import com.example.mapnaverapi.map.viewmodel.MapViewModel
import com.example.mapnaverapi.util.VerticalItemDecoration
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LocationListFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLocationListBinding
    private lateinit var locationListAdapter: LocationListAdapter
    private val viewModel: MapViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        updateSearchList()
    }

    fun initRecyclerView() {
        locationListAdapter = LocationListAdapter {
            viewModel.changeAddressSelected(it)
            dismiss()
        }
        binding.recyclerviewLocation.apply {
            adapter = locationListAdapter
            addItemDecoration(VerticalItemDecoration(15))
        }
    }

    fun updateSearchList() {
        val recyclerviewList = mutableListOf<AddressModel>()
        viewModel.locationListSearched.observe(viewLifecycleOwner, androidx.lifecycle.Observer { addressList ->
            addressList.all {
                recyclerviewList.add(
                    AddressModel(
                        it.getAddressLine(0),
                        it.latitude,
                        it.longitude
                    )
                )
            }
        })
        viewModel.changeAddressListModel(recyclerviewList)
        locationListAdapter.submitList(viewModel.addressListModel.value)
    }

    override fun getTheme() = R.style.BottomSheetDialogTheme
}