package com.example.mapservice.mapservice.friendslist.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapservice.mapservice.friendslist.model.ResponseFriendsList
import com.example.mapservice.mapservice.friendslist.repository.FriendsListRepository
import com.example.mapservice.mapservice.utils.DataState
import kotlinx.coroutines.launch

class FriendsListViewModel @ViewModelInject constructor(
    val friendsListRepository: FriendsListRepository
): ViewModel() {
    private val _friendsList = MutableLiveData<DataState<ResponseFriendsList>>()
    val friendsList: LiveData<DataState<ResponseFriendsList>>
        get() = _friendsList

    init {
        getFriendsList(2)
    }

    fun getFriendsList(page: Int) {
        viewModelScope.launch {
            _friendsList.postValue(DataState.loading(null))
            friendsListRepository.getFriendsList(page).let {
                if (it.isSuccessful){
                    _friendsList.postValue(DataState.success(it.body()))
                } else {
                    _friendsList.postValue(DataState.error(null, it.errorBody().toString()))
                }
            }
        }
    }
}