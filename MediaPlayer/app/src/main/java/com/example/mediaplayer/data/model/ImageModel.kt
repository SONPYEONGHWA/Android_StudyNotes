package com.example.mediaplayer.data.model

import android.net.Uri
import androidx.databinding.ObservableField
import java.util.*

data class ImageModel(
    val imageDataPath: Uri,
    val dateTaken : Long,
    val displayName: String,
    val id: Long,
    var isSelected: Boolean
)