package com.example.daggerhiltstudy.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerhiltstudy.model.DataState
import com.example.daggerhiltstudy.model.ResponseUsers
import com.example.daggerhiltstudy.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _userList = MutableLiveData<DataState<ResponseUsers>>()
    val userList: LiveData<DataState<ResponseUsers>>
        get() = _userList

    init {
        getProfileList()
    }
    private fun getProfileList() {
        viewModelScope.launch {
            _userList.postValue(DataState.loading(null))
            userRepository.getUserList().let {
                if (it.isSuccessful){
                    Log.d("succes load datas", it.body().toString())
                    _userList.postValue(DataState.success(it.body()))
                } else {
                    _userList.postValue(DataState.error(it.errorBody().toString(), null))
                }
            }
        }
    }
}