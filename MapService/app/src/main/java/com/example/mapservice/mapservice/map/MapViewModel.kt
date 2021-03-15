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

    private val _currentLatLng = MutableLiveData<Location>()
    val currentLatLng: LiveData<Location>
        get() = _currentLatLng

    fun changeCurrentLatLng(location: Location?) {
        _currentLatLng.value = location
    }


    fun getLocation(userLocation: Location?) {
        if (userLocation != null) {
            _latitude.value = userLocation.latitude
            _longtitude.value = userLocation.longitude

            try {
                _resultList.value = geocoder.getFromLocation(
                    latitude.value!!, longtitude.value!!, 1
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (resultList != null) {
                Log.d("CheckCurrentLocation", resultList.value!![0].getAddressLine(0))
            }
        }
    }
}