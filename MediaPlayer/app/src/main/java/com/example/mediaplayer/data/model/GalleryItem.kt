package com.example.mediaplayer.data.model

import android.net.Uri
import androidx.databinding.BaseObservable

class GalleryItem(val imageModel: ImageModel): BaseObservable() {
    val uri: Uri = imageModel.imageDataPath
    fun getId(): Long = imageModel.id
}