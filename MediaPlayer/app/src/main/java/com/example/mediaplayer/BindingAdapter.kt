package com.example.mediaplayer

import android.net.Uri
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("uploadImage")
fun uploadImage(view:ImageView, uri: Uri) {
    Glide.with(view.context)
        .load(uri)
        .into(view)
}