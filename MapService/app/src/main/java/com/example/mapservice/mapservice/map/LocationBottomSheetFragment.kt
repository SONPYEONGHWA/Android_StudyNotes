package com.example.mapservice.mapservice.map

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mapservice.R
import com.example.mapservice.databinding.FragmentLocationBottomSheetBinding
import com.example.mapservice.mapservice.utils.VerticalItemDecoration
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.daum.mf.map.api.MapView

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

        initRecyclerView()
        getLocationResultSearched()
        updateBottomSheetData()
    }

    private fun initRecyclerView() {
        adapter = LocationBottomSheetAdapter {
          viewModel.changeLocationSelected(it)
            dismiss()
        }

        binding.recyclerviewLocation.adapter = adapter
        binding.recyclerviewLocation.addItemDecoration(VerticalItemDecoration(10))
    }

    fun getLocationResultSearched() {
        var recyclerViewList = mutableListOf<AddressModel>()
        val resultList = viewModel.resultList

        resultList.observe(viewLifecycleOwner, Observer {
            for (i in resultList.value!!.indices) {
                recyclerViewList.add(
                    AddressModel(
                        resultList.value!![i].getAddressLine(0).toString(),
                        resultList.value!![i].latitude,
                        resultList.value!![i].longitude
                    )
                )
            }
            viewModel.changeLocaitonResult(recyclerViewList)
        })
    }

    fun updateBottomSheetData() {
        viewModel.locationResultSearched.observe(viewLifecycleOwner, Observer {
            Log.e("Search Location List: ", it.toString())
            adapter.submitList(it)
        }
        )
    }
}