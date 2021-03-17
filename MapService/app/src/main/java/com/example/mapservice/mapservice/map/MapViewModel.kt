package com.example.mapservice.mapservice.map

import android.location.*
import android.os.Bundle
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import java.io.IOException

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

    private val _locationResultSearched = MutableLiveData<List<AddressModel>>()
    val locationResultSearched: LiveData<List<AddressModel>>
        get() = _locationResultSearched

    private val _locationSelected = MutableLiveData<AddressModel>()
    val locationSelected: LiveData<AddressModel>
        get() = _locationSelected

    val searchAddressQuery = MutableLiveData<String>()

    fun changeResultList(list: List<Address>) {
        _resultList.value = list
    }

    fun changeLocaitonResult(list: List<AddressModel>) {
        _locationResultSearched.value = list
    }

    fun changeLocationSelected(address: AddressModel) {
        _locationSelected.value = address
    }

    fun changeLatitude(latitude: Double) {
        _latitude.value = latitude
    }

    fun changeLongtitude(longtitude: Double) {
        _longtitude.value = longtitude
    }

    fun changeCurrentLocation(location: String) {
        _currentLocation.value = location
    }

    fun getLocation(): LocationListener {
        val mLocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                if (location != null) {
                    _latitude.value = location.latitude
                    _longtitude.value = location.longitude
                    getAddress()
                    Log.e("Your Location: ", "${latitude.value}, ${longtitude.value}")
                }
                setMarker()
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                super.onStatusChanged(provider, status, extras)
            }
        }
        return mLocationListener
    }

    fun getAddress() {
        try {

            changeResultList(
                geocoder.getFromLocation(
                    latitude.value!!,
                    longtitude.value!!,
                    1
                )
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (resultList?.value != null) {
            _currentLocation.value = resultList?.value!![0].getAddressLine(0)
        }
    }

    fun setMarker(): MapPOIItem {
        val marker = MapPOIItem()
        marker.itemName = currentLocation.value
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(latitude.value!!, longtitude.value!!)
        marker.markerType = MapPOIItem.MarkerType.BluePin
        return marker
    }

    fun searchAddress(string: String) {
        try {
            changeResultList(geocoder.getFromLocationName(string, 10))
            Log.e("result :", resultList?.value.toString())

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}