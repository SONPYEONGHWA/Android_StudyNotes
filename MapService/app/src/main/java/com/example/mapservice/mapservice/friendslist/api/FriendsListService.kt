package com.example.mapservice.mapservice.friendslist.api

import com.example.mapservice.mapservice.friendslist.model.ResponseFriendsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendsListService {
    @GET("/api/users")
    suspend fun getFriendsList(@Query("page") page: Int): Response<ResponseFriendsList>
}