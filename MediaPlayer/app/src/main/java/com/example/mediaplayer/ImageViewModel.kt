package com.example.mediaplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageViewModel: ViewModel() {
    private val _images = MutableLiveData<List<ImageModel>>()
    val images: LiveData<List<ImageModel>>
    get() = _images

    fun changeImages(list: List<ImageModel>){
        _images.value = list
    }
}