package com.example.climateseoul.climateinfo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climateseoul.BuildConfig
import com.example.climateseoul.climateinfo.model.IotVdata
import com.example.climateseoul.climateinfo.repository.ClimateInfoRepository
import com.example.climateseoul.util.Resource
import kotlinx.coroutines.launch

class ClimateInfoViewModel @ViewModelInject constructor(
    private val climateInfoRepository: ClimateInfoRepository
) : ViewModel() {
    private val _climateList = MutableLiveData<Resource<IotVdata>>()
    val climateList: LiveData<Resource<IotVdata>>
        get() = _climateList

    init {
        getClimateInfo(BuildConfig.SEOUL_CLIMATE_API)
    }

    private fun getClimateInfo(apiKey: String) {

        viewModelScope.launch {
            _climateList.postValue(Resource.loading(null))
            climateInfoRepository.getClimateInfo(apiKey).let {
                if (it.isSuccessful) {
                    _climateList.postValue(Resource.success(it.body()))
                } else {
                    _climateList.postValue(Resource.error(null, it.errorBody().toString()))
                }
            }
        }
    }
}