package com.example.rxjavasample.searchmovie.remote.api

import com.example.rxjavasample.searchmovie.remote.model.MovieResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchMovieService {
    @GET("/v1/search/movie.json")
    fun getMovieList(@Query("query") query: String, @Query("country") country: String?): Single<MovieResponseModel>
}