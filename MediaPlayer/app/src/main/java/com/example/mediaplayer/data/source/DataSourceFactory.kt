package com.example.mediaplayer.data.source

import android.content.ContentResolver
import androidx.paging.DataSource
import com.example.mediaplayer.data.model.ImageModel
import javax.inject.Inject

class DataSourceFactory @Inject constructor(
    private val galleryDataSource: GalleryDataSource
    ): DataSource.Factory<Int, ImageModel>() {
    override fun create(): DataSource<Int, ImageModel> {
        return galleryDataSource
    }
}