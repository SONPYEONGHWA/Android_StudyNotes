package com.example.mediaplayer.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mediaplayer.data.model.ImageModel
import com.example.mediaplayer.data.source.DataSourceFactory
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val datasource: DataSourceFactory
) {
    fun getImageList():LiveData<PagedList<ImageModel>> {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()

        return LivePagedListBuilder(datasource, config).build()
    }
}