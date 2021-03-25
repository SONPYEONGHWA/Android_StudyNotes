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
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class SearchMovieViewModel @ViewModelInject constructor(
    private val searchMovieRepository: SearchMovieRepository,
    private val searchHistoryRepository: SearchHistoryRepository
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _movieList = MutableLiveData<MovieResponseModel>()
    val movieList: LiveData<MovieResponseModel>
        get() = _movieList

    val searchQuery: MutableLiveData<String> = MutableLiveData<String>()

    private val _country = MutableLiveData<String>()
    val country: LiveData<String>
        get() = _country

    private val _queryList = MutableLiveData<List<SearchEntity>>()
    val queryList: LiveData<List<SearchEntity>>
        get() = _queryList

    fun changeCountryFilter(country: String) {
        _country.value = country
    }

    init {
        getSearchHistory()
    }

    @SuppressLint("CheckResult")
    fun getMovieList() {
        searchMovieRepository.getMovieList(
            searchQuery.value!!,
            country.value
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _movieList.postValue(it)
                insertHistory() },
                { it.printStackTrace() }
            )
    }

    private fun insertHistory() {
        val searchEntity = SearchEntity(null, searchQuery.value!!)

        compositeDisposable.add(
            Observable.fromCallable {
                searchHistoryRepository.insert(
                    searchEntity
                )
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { getSearchHistory() },
                    { it.printStackTrace() }
                )
        )
    }

    @SuppressLint("CheckResult")
    fun getSearchHistory() {
        compositeDisposable.add(
            searchHistoryRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _queryList.value = it },
                    { it.printStackTrace() }
                )
        )
    }

    fun deleteHistory(searchEntity: SearchEntity) {
        compositeDisposable.add(Observable.fromCallable {
            searchHistoryRepository.delete(searchEntity)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { getSearchHistory() },
                { it.printStackTrace() }
            )
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}