package com.example.mediaplayer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mediaplayer.data.model.ImageModel
import com.example.mediaplayer.data.source.GalleryDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val datasource: GalleryDataSource
) {
    fun getImageList(): Flow<PagingData<ImageModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 20
        ),
            pagingSourceFactory = {
                datasource
            }).flow
    }
}