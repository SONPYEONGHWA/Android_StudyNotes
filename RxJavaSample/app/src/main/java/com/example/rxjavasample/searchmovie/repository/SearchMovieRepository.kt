package com.example.rxjavasample.searchmovie.repository

import com.example.rxjavasample.searchmovie.api.SearchMovieService
import com.example.rxjavasample.searchmovie.model.MovieResponseModel
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SearchMovieRepository @Inject constructor(
    val searchMovieService: SearchMovieService
) {
    fun getMovieList(query: String, country: String?): Single<MovieResponseModel> =
        searchMovieService.getMovieList(query, country)
}