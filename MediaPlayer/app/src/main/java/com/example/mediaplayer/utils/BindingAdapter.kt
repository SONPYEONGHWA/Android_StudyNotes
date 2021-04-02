package com.example.mediaplayer.utils

import android.graphics.PorterDuff
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mediaplayer.R

@BindingAdapter("uploadImage")
fun uploadImage(view:ImageView, uri: Uri) {
    Glide.with(view.context)
        .load(uri)
        .into(view)
}

@BindingAdapter("selectedMarker")
fun setSelectedMarker(view: ImageView, isSelected: Boolean) {
    if (isSelected) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}
@BindingAdapter("colorFilter")
fun setColorFilter(view:ImageView, isSelected: Boolean){
    if (isSelected) {
        view.setColorFilter(R.color.gray, PorterDuff.Mode.XOR)
    } else {
        view.clearColorFilter()
    }
}