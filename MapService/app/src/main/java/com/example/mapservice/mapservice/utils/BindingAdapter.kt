package com.example.mapservice.mapservice

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide

@BindingAdapter("setImageUrl")
fun setImageUrl(imageview: ImageView, url: String?) {
    Glide.with(imageview.context)
        .load(url)
        .into(imageview)
}

@InverseBindingAdapter(attribute = "android:text", event = "textAttrChanged")
fun getTextString(editText: EditText): String {
    return editText.text.toString()
}

@BindingAdapter("textAttrChanged")
fun setTextWatcher(view: EditText, textAttrChanged: InverseBindingListener) {
    view.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            textAttrChanged.onChange()
        }
    })
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}