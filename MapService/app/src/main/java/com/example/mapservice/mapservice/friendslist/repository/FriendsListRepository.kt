package com.example.mapservice.mapservice.friendslist.repository

import com.example.mapservice.mapservice.friendslist.api.FriendsListService
import javax.inject.Inject

class FriendsListRepository @Inject constructor(
    val friendsListService: FriendsListService
) {
    suspend fun getFriendsList(page: Int) = friendsListService.getFriendsList(page)
}