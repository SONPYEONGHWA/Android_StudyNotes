package com.example.mediaplayer.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mediaplayer.data.model.ImageModel
import com.example.mediaplayer.data.source.DataSourceFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val galleryDataSource: DataSourceFactory
) : ViewModel() {

    private val config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(20)
        .setPageSize(20)
        .setEnablePlaceholders(false)
        .build()

    val galleryLiveData = LivePagedListBuilder(galleryDataSource, config).build()
}