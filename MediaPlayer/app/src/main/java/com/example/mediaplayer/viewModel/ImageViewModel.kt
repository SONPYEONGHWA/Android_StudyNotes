package com.example.mediaplayer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mediaplayer.data.repository.GalleryRepository
import com.example.mediaplayer.data.model.ImageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository
) : ViewModel() {

    fun galleryLiveData(): Flow<PagingData<ImageModel>> =
        galleryRepository.getImageList().cachedIn(viewModelScope)

    private val _imageList = MutableLiveData<List<ImageModel>>()
    val imageList: LiveData<List<ImageModel>>
    get() = _imageList

    fun changeImageList(list: List<ImageModel>){
        _imageList.value = list
    }
}