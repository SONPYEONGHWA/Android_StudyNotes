package com.example.mediaplayer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mediaplayer.data.repository.GalleryRepository
import com.example.mediaplayer.data.model.ImageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository
) : ViewModel() {

    val galleryLiveData: LiveData<PagedList<ImageModel>> =
        galleryRepository.getImageList()

    private val _imageList = MutableLiveData<List<ImageModel>>()
    val imageList: LiveData<List<ImageModel>>
    get() = _imageList

    fun changeImageList(list: List< ImageModel>){
        _imageList.value = list
    }
}