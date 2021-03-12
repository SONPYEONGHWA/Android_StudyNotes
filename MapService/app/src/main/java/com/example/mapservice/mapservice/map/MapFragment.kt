package com.example.mapservice.mapservice.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mapservice.databinding.FragmentMapBinding
import com.example.mapservice.mapservice.MainActivity
import net.daum.mf.map.api.MapView

class MapFragment : Fragment() {
    private lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMapView()
    }

    private fun initMapView() {
        val mapView = MapView(context as MainActivity)
        val mapViewContainer = binding.mapview
        mapViewContainer.addView(mapView)
    }
}