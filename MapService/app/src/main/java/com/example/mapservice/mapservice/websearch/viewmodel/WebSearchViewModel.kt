package com.example.mapservice.mapservice.websearch.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapservice.mapservice.utils.DataState
import com.example.mapservice.mapservice.websearch.model.SearchResponseModel
import com.example.mapservice.mapservice.websearch.repository.SearchRepository
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject

class WebSearchViewModel @ViewModelInject constructor(
    val searchRepository: SearchRepository
) : ViewModel() {
    private val _searchResponse = MutableLiveData<DataState<SearchResponseModel>>()
    val searchResponse: LiveData<DataState<SearchResponseModel>>
        get() = _searchResponse


    fun getSearchResults(query: String) {
        viewModelScope.launch {
//            kotlin.runCatching { searchRepository.getSearchResults(query) }
//                .onSuccess { _searchResponse.postValue(DataState.success(it.body())) }
//                .onFailure { _searchResponse.postValue(DataState.error(null, it.toString())) }
            _searchResponse.postValue(DataState.loading(null))
            searchRepository.getSearchResults(query).let {
                if (it.isSuccessful) {
                    _searchResponse.postValue(DataState.success(it.body()))
                } else {
                    _searchResponse.postValue(DataState.error(null, it.errorBody().toString()))
                }
            }
        }
    }

    fun printErrorBody(responseBody: ResponseBody?){
        val e = responseBody ?: return
        val ob = JSONObject(e.string())
        Log.e("error: ", ob.toString())
    }
}