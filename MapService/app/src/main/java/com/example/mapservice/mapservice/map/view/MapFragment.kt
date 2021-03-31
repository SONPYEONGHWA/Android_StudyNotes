package com.example.mapservice.mapservice.map.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mapservice.databinding.FragmentMapBinding
import com.example.mapservice.mapservice.map.viewmodel.MapViewModel
import com.example.mapservice.mapservice.utils.PermissionUtility
import com.example.mapservice.mapservice.utils.PermissionUtility.REQUIRED_PERMISSIONS
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class MapFragment : Fragment() {
    private lateinit var binding: FragmentMapBinding
    private lateinit var mapView: MapView
    private val viewModel: MapViewModel by activityViewModels()
    private val bottomSheet = LocationBottomSheetFragment()
    private var locationManager: LocationManager? = null
    private val PERMISSIONS_REQUEST_CODE = 100
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMapView()
        checkPermissions()
        searchLocation()
        markSearchedLocation()
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

    @SuppressLint("MissingPermission")
    private fun checkPermissions() {
        if (PermissionUtility.checkLocationPermissions(mContext)) {
            findMyLocation()
        } else {
            Toast.makeText(mContext, "위치 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                PERMISSIONS_REQUEST_CODE
            )
            findMyLocation()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            PERMISSIONS_REQUEST_CODE -> {
                if(resultCode == PackageManager.PERMISSION_GRANTED) {
                    checkPermissions()
                } else {
                    Toast.makeText(requireContext(), "위치 접근 권한을 허용해주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun findMyLocation() {
        viewModel.getLocationData().observe(viewLifecycleOwner, Observer { location ->
            binding.buttonMyLocation.setOnClickListener {
                mapView.removeAllPOIItems()
                setMarker("My Location", location.latitude, location.longtitude)
                moveCamera(location.latitude, location.longtitude)
            }
        })
    }

    private fun setMarker(name: String, latitude: Double, longtitude: Double) {
        val marker = MapPOIItem()
        marker.itemName = name
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longtitude)
        marker.markerType = MapPOIItem.MarkerType.BluePin
        mapView.addPOIItem(marker)
    }

    private fun moveCamera(latitude: Double, longtitude: Double) {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longtitude), true)
    }

    private fun searchLocation() {
        binding.buttonSearchAddress.setOnClickListener {
            viewModel.searchLocation()
            mapView.removeAllPOIItems()
            viewModel.resultList.observe(viewLifecycleOwner, Observer{list ->
                if (!bottomSheet.isAdded) bottomSheet.show(childFragmentManager, "bottomsheet")
                list.forEach { document ->
                    setMarker(document.placeName, document.latitude.toDouble(), document.longtitude.toDouble())
                    moveCamera(document.latitude.toDouble(), document.longtitude.toDouble())
                }
            })
        }
    }

    private fun markSearchedLocation() {
        viewModel.locationSelected.observe(viewLifecycleOwner, Observer {
            mapView.removeAllPOIItems()
            setMarker(it.placeName, it.latitude, it.longtitude)
            moveCamera( it.latitude, it.longtitude)
        })
    }
}