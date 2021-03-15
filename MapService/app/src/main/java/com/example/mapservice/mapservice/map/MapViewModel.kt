package com.example.mapservice.mapservice.map

import android.app.Activity
import android.content.Context
import android.location.*
import android.os.Bundle
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapservice.mapservice.application.MapServiceApplication
import com.example.mapservice.mapservice.utils.PermissionUtility
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.io.IOException
import java.util.*

class MapViewModel @ViewModelInject constructor(
    val geocoder: Geocoder
) : ViewModel() {

    private val _latitude = MutableLiveData<Double>()
    val latitude: LiveData<Double>
        get() = _latitude

    private val _longtitude = MutableLiveData<Double>()
    val longtitude: LiveData<Double>
        get() = _longtitude

    private val _resultList = MutableLiveData<List<Address>>()
    val resultList: LiveData<List<Address>>
        get() = _resultList

    private val _currentLocation = MutableLiveData<String>()
    val currentLocation: LiveData<String>
    get() = _currentLocation

    var mLocationListener: LocationListener? = null

    fun getLocation(mapView: MapView) {
        mLocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                if (location != null) {
                    _latitude.value = location.latitude
                    _longtitude.value = location.longitude
                    getAddress()
                    Log.e("Your Location: ", "${latitude.value}, ${longtitude.value}")
                }

                markOnMap(mapView)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                super.onStatusChanged(provider, status, extras)
            }

            override fun onProviderEnabled(provider: String) {
                super.onProviderEnabled(provider)
            }

            override fun onProviderDisabled(provider: String) {
                super.onProviderDisabled(provider)
            }
        }
    }

    fun getAddress() {
        try{
            _resultList.value =
                geocoder.getFromLocation(
                latitude.value!!,
                    longtitude.value!!,
                    1
            )
        } catch (e: IOException){
            e.printStackTrace()
        }

        if (resultList.value != null) {
            _currentLocation.value = resultList.value!![0].getAddressLine(0)
        }
    }


    fun markOnMap(mapView: MapView) {
        mapView.removeAllPOIItems()
        val marker = MapPOIItem()
        marker.itemName = currentLocation.value
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(latitude.value!!, longtitude.value!!)
        marker.markerType = MapPOIItem.MarkerType.BluePin
        mapView.addPOIItem(marker)
        mapView.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(
                latitude.value!!,
               longtitude.value!!
            ), true
        )
    }
}