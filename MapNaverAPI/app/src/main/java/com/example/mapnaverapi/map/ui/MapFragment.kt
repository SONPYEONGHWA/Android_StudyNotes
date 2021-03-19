package com.example.mapnaverapi.map.ui

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mapnaverapi.base.BaseFragment
import com.example.mapnaverapi.databinding.FragmentMapBinding
import com.example.mapnaverapi.map.viewmodel.MapViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BaseFragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentMapBinding
    private val viewModel: MapViewModel by activityViewModels()
    private val locationListFragment = LocationListFragment()
    private lateinit var marker: Marker
    private lateinit var mapView: MapView
    private lateinit var naverMap: NaverMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        mapView = binding.mapview
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        marker = Marker()
        findLocation()
        findMyLocation()
        markLocationSearched()
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
    }

    override fun permissionGranted(requestCode: Int) {
    }

    override fun permissionDenied(requestCode: Int) {
        Toast.makeText(requireContext(), "위치 접근 권한을 허용해주세요", Toast.LENGTH_SHORT).show()
    }

    private fun findMyLocation() {
        requirePermissions(permissions, LOCATION_PERMISSION_REQUEST_CODE)
        viewModel.getLocationData().observe(viewLifecycleOwner, Observer{location ->
            binding.buttonMyLocation.setOnClickListener {
                marker.map = null
                marker.position = LatLng(location.latitude, location.longtitude)
                val cameraUpdate = CameraUpdate.scrollTo(marker.position).animate(CameraAnimation.Easing)
                marker.map = naverMap
                naverMap.moveCamera(cameraUpdate)
            }
        })
    }

    private fun findLocation() {
        binding.buttonSearchAddress.setOnClickListener {
            viewModel.searchLocation(binding.edittextSearchAddress.text.toString())
            if (viewModel.locationListSearched.value.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "검색결과가 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                showLocationList()
            }
        }
    }

    private fun showLocationList() {
        if (!locationListFragment.isAdded) {
            locationListFragment.show(childFragmentManager, "tag")
        }
    }

    private fun markLocationSearched() {
        viewModel.addressSelected.observe(viewLifecycleOwner, Observer {
            marker.map = null
            marker.position = LatLng(it.latitude, it.longtitude)
            val cameraUpdate = CameraUpdate.scrollTo(marker.position).animate(CameraAnimation.Easing)
            marker.map = naverMap
            naverMap.moveCamera(cameraUpdate)
            Log.e("update", it.address)
        })
    }

    companion object {
        private val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}