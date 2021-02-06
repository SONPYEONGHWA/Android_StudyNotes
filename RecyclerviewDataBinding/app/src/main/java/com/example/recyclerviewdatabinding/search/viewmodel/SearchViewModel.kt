package com.example.recyclerviewdatabinding.search.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.recyclerviewdatabinding.network.RetrofitClient
import com.example.recyclerviewdatabinding.search.repository.SearchRepository
import com.example.recyclerviewdatabinding.search.model.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val _docsList = MutableLiveData<ArrayList<SearchResponse.Documents>>()
    val docsList: LiveData<ArrayList<SearchResponse.Documents>>
        get() = _docsList

    fun changeDocsList(docsList: ArrayList<SearchResponse.Documents>){
        _docsList.value = docsList
    }

    private val repository = SearchRepository(application)
    private val searchHistoryList = repository.getAll()

    fun getAll(): LiveData<List<SearchResponse.Documents>>{
        return searchHistoryList
    }

    fun insert(documents: SearchResponse.Documents) {
        repository.insert(documents)
    }

    fun insertAll(searchList: List<SearchResponse.Documents>){
        repository.insertAll(searchList)
    }

    fun delete(documents: SearchResponse.Documents) {
        repository.delete(documents)
    }

    fun searchDocs(searchWord: String) {
        var docsList = mutableListOf<SearchResponse.Documents>()
        val token = "KakaoAK bccddec2477515123ee06ae249c39f95"
        val call: Call<SearchResponse> = RetrofitClient.retrofitInterface.searchInterface(
            token,
            searchWord
        )

        call.enqueue(object: Callback<SearchResponse>{
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        docsList = it.documents
                        changeDocsList(docsList as ArrayList<SearchResponse.Documents>)
                    } ?: Log.d("error: ", "${response.errorBody()}")
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("error: ", t.toString())
            }
        })
    }
}