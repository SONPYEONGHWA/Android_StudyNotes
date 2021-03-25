package com.example.rxjavasample.searchmovie.remote.repository

import com.example.rxjavasample.searchmovie.remote.api.SearchMovieService
import com.example.rxjavasample.searchmovie.remote.model.MovieResponseModel
import io.reactivex.Single
import javax.inject.Inject

class SearchMovieRepository @Inject constructor(
    val searchMovieService: SearchMovieService
) {
    fun getMovieList(query: String, country: String?): Single<MovieResponseModel> =
        searchMovieService.getMovieList(query, country)
}