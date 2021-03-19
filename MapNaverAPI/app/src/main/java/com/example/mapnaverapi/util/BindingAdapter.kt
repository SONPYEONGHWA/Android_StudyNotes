package com.example.mapnaverapi

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData

@BindingAdapter("android:text")
fun setTextString(view: EditText, content: MutableLiveData<String>) {
    if (content == null){
        view.setText("")
    } else {
        if (view.text.toString() != content.value) view.setText(content.value)
    }

}

@InverseBindingAdapter(attribute = "android:text", event = "textAttrChanged")
fun getTextString(editText: EditText): String? {
    return editText.text.toString()
}

@BindingAdapter("textAttrChanged")
fun setTextWatcher(view: EditText, textAttrChanged: InverseBindingListener) {
    view.addTextChangedListener(object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            textAttrChanged.onChange()
        }
    })
}