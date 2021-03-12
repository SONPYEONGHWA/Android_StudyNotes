package com.example.mapservice.mapservice.friendslist.model

import com.google.gson.annotations.SerializedName

data class ResponseFriendsList(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    @SerializedName("data")
    val userList: List<UserList>,
    val support: Support
) {
    data class UserList(
        val avatar: String,
        val email: String,
        val first_name: String,
        val id: Int,
        val last_name: String
    )

    data class Support(
        val text: String,
        val url: String
    )
}