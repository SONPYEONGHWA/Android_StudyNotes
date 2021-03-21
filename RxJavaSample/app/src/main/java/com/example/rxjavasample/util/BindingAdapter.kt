package com.example.rxjavasample

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide

@BindingAdapter("setImageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

@BindingAdapter("android:text")
fun setTextString(textview: TextView, content: MutableLiveData<String>) {
    if (content == null) {
        textview.text = ""
    } else {
        if (textview.text.toString() != content.value) textview.text = content.value
    }
}

@BindingAdapter("textAttrChanged")
fun setTextWatcher(editText: EditText, textAttrChanged: InverseBindingListener) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            textAttrChanged.onChange()
        }
    })
}

@InverseBindingAdapter(attribute = "android:text", event = "textAttrChanged")
fun getTextString(editText: EditText): String? {
    return editText.text.toString()
}
