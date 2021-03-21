package com.example.rxjavasample.searchmovie.api

import com.example.rxjavasample.searchmovie.model.MovieResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchMovieService {
    @GET("/v1/search/movie.json")
    fun getMovieList(@Query("query") query: String): Observable<MovieResponseModel>
}