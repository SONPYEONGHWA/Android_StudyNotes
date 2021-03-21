package com.example.rxjavasample.searchmovie.repository

import com.example.rxjavasample.searchmovie.api.SearchMovieService
import com.example.rxjavasample.searchmovie.model.MovieResponseModel
import io.reactivex.Observable
import javax.inject.Inject

class SearchMovieRepository @Inject constructor(
    val searchMovieService: SearchMovieService
) {
    fun getMovieList(query: String): Observable<MovieResponseModel> =
        searchMovieService.getMovieList(query)
}