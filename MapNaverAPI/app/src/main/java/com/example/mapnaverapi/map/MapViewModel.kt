package com.example.mapnaverapi.map

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch

class MapViewModel @ViewModelInject constructor(
        @ApplicationContext context: Context,
val geocoder: Geocoder) : ViewModel() {
    private val locationData = LocationLiveData(context)

    fun getLocationData() = locationData

    val locationSearched: MutableLiveData<String> = MutableLiveData<String>()

    private val _locationListSearched = MutableLiveData<List<Address>>()
    val locationListSearched: LiveData<List<Address>>
    get() = _locationListSearched

    private val _addressListModel = MutableLiveData<List<AddressModel>>()
    val addressListModel: LiveData<List<AddressModel>>
    get() = _addressListModel

    private val _addressSelected = MutableLiveData<AddressModel>()
    val addressSelected: LiveData<AddressModel>
    get() = _addressSelected

    fun changeAddressListModel(list: List<AddressModel>) {
        _addressListModel.value = list
    }

    fun changeAddressSelected(addressModel: AddressModel) {
        _addressSelected.value = addressModel
    }


    fun searchLocation(string: String) {
        viewModelScope.launch {
            _locationListSearched.value =
                geocoder.getFromLocationName(string,15)
            Log.e("ggg", locationListSearched.toString())
        }
    }
}