package com.example.rxjavasample.searchmovie.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rxjavasample.base.BaseViewModel
import com.example.rxjavasample.searchmovie.model.MovieResponseModel
import com.example.rxjavasample.searchmovie.repository.SearchMovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchMovieViewModel @ViewModelInject constructor(
    val searchMovieRepository: SearchMovieRepository
) : BaseViewModel() {

    private val _movieList = MutableLiveData<MovieResponseModel>()
    val movieList: LiveData<MovieResponseModel>
        get() = _movieList

    val searchQuery: MutableLiveData<String> = MutableLiveData<String>()

    @SuppressLint("CheckResult")
    fun getMovieList() {
        searchMovieRepository.getMovieList(
            searchQuery.value!!
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _movieList.postValue(it)
                Log.e("update movie list", movieList.value.toString())
            }, {
                Log.e("error :", it.stackTraceToString())
            })
    }
}