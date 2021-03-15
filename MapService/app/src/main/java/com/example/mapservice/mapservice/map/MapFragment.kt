package com.example.mapservice.mapservice.map

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mapservice.databinding.FragmentMapBinding
import com.example.mapservice.mapservice.MainActivity
import com.example.mapservice.mapservice.application.MapServiceApplication
import com.example.mapservice.mapservice.utils.PermissionUtility
import com.example.mapservice.mapservice.utils.PermissionUtility.REQUIRED_PERMISSIONS
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class MapFragment : Fragment() {
    private lateinit var binding: FragmentMapBinding
    private lateinit var mapView: MapView
    private val viewModel: MapViewModel by activityViewModels()
    private var locationManager: LocationManager? = null
    private val PERMISSIONS_REQUEST_CODE = 100
    private lateinit var mContext: Context

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
        markCurrentLocation()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun initMapView() {
        mapView = MapView(mContext)
        val mapViewContainer = binding.mapview
        mapViewContainer.addView(mapView)
    }

    private fun markCurrentLocation() {
        binding.buttonMyLocation.setOnClickListener {
            getLatLng()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLatLng() {
        viewModel.getLocation(mapView)
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (PermissionUtility.checkLocationPermissions(mContext)) {
            locationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                3000L,
                30f,
                viewModel.mLocationListener!!
            )
            viewModel.getLocation(mapView)
        } else {
            Toast.makeText(mContext, "위치 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                PERMISSIONS_REQUEST_CODE
            )
            viewModel.getLocation(mapView)
        }
    }
}