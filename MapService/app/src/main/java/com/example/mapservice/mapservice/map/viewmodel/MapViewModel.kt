package com.example.mapservice.mapservice.map.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapservice.mapservice.map.data.LocationLiveData
import com.example.mapservice.mapservice.map.data.repository.LocationRepository
import com.example.mapservice.mapservice.map.model.LocationSearchResponse
import com.example.mapservice.mapservice.map.model.LocationSelectedModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MapViewModel @ViewModelInject constructor(
    private val locationRepository: LocationRepository,
    @ApplicationContext context: Context
) : ViewModel() {

    private val locationData = LocationLiveData(context)
    fun getLocationData() = locationData

    private val _resultList = MutableLiveData<List<LocationSearchResponse.Document>>()
    val resultList: LiveData<List<LocationSearchResponse.Document>>
        get() = _resultList

    private val _locationSelected = MutableLiveData<LocationSelectedModel>()
    val locationSelected: LiveData<LocationSelectedModel>
        get() = _locationSelected

    fun changeLocationSelected(location: LocationSelectedModel) {
        _locationSelected.value = location
    }

    val searchAddressQuery = MutableLiveData<String>()

    @SuppressLint("CheckResult")
    fun searchLocation() {
        Log.e("query ", searchAddressQuery.value!!)
        locationRepository.getLocationSearched(searchAddressQuery.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ resopnse ->
                _resultList.postValue(resopnse.documents)
            }, {
                it.printStackTrace()
            })
    }
}