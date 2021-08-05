package com.example.mapservice.mapservice.map.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapservice.mapservice.map.data.entity.LocationEntity
import com.example.mapservice.mapservice.map.data.LocationLiveData
import com.example.mapservice.mapservice.map.data.repository.LocationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MapViewModel @ViewModelInject constructor(
    private val locationRepository: LocationRepository,
    @ApplicationContext context: Context
) : ViewModel() {

    private val locationData = LocationLiveData(context)
    fun getLocationData() = locationData

    private val _resultList = MutableLiveData<List<LocationEntity>>()
    val resultList: LiveData<List<LocationEntity>>
        get() = _resultList

    private val _locationSelected = MutableLiveData<LocationEntity>()
    val locationSelected: LiveData<LocationEntity>
        get() = _locationSelected

    fun changeLocationSelected(location: LocationEntity) {
        _locationSelected.value = location
    }

    val searchAddressQuery = MutableLiveData<String>()

    @SuppressLint("CheckResult")
    fun searchLocation() {
        locationRepository.getLocationSearched(searchAddressQuery.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ locations ->
                _resultList.postValue(locations)
            }, {
                it.printStackTrace()
            })
    }
}