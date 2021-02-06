package com.example.recyclerviewdatabinding.bottomsheet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerviewdatabinding.bottomsheet.moel.MainBottomSheetModel

class MainBottomSheetViewModel : ViewModel() {
    private val _userList = MutableLiveData<ArrayList<MainBottomSheetModel>>()
    val userList: LiveData<ArrayList<MainBottomSheetModel>>
        get() = _userList

    fun changeUserList(userList: ArrayList<MainBottomSheetModel>) {
        _userList.value = userList
    }
}