package com.example.mediaplayer

import android.content.ContentResolver
import androidx.paging.DataSource

class DataSourceFactory(private val contentResolver: ContentResolver): DataSource.Factory<Int, ImageModel>() {
    override fun create(): DataSource<Int, ImageModel> {
        return GalleryDataSource(contentResolver)
    }
}