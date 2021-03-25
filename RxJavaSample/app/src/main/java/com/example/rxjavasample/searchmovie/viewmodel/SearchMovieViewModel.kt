package com.example.rxjavasample.searchmovie.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rxjavasample.searchmovie.local.entity.SearchEntity
import com.example.rxjavasample.searchmovie.local.repository.SearchHistoryRepository
import com.example.rxjavasample.searchmovie.remote.model.MovieResponseModel
import com.example.rxjavasample.searchmovie.remote.repository.SearchMovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class SearchMovieViewModel @ViewModelInject constructor(
    private val searchMovieRepository: SearchMovieRepository,
    private val searchHistoryRepository: SearchHistoryRepository
    ) : ViewModel() {

    private val _movieList = MutableLiveData<MovieResponseModel>()
    val movieList: LiveData<MovieResponseModel>
        get() = _movieList

    val searchQuery: MutableLiveData<String> = MutableLiveData<String>()

    private val _genreMovie = MutableLiveData<Int>()
    val genreMovie: LiveData<Int>
        get() = _genreMovie

    private val _country = MutableLiveData<String>()
    val country: LiveData<String>
    get() = _country

    private val _queryList = MutableLiveData<List<SearchEntity>>()
    val queryList: LiveData<List<SearchEntity>>
    get() = _queryList


    init {
        getSearchHistory()
    }

    fun changeCountryFilter(country: String) {
        _country.value = country
    }

    @SuppressLint("CheckResult")
    fun getMovieList() {
        searchMovieRepository.getMovieList(
            searchQuery.value!!,
            country.value
            ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _movieList.postValue(it)
                insertHistory()
                getSearchHistory()
            }, {
                Log.e("error :", it.stackTraceToString())
            })
    }

    private fun insertHistory() {
        viewModelScope.launch {
            searchHistoryRepository.insert(
                SearchEntity(
                    null,
                    searchQuery.value!!
                )
            )
        }
    }

    fun getSearchHistory() {
        viewModelScope.launch {
            _queryList.postValue(searchHistoryRepository.getAll())
        }
    }
}