package com.example.climateseoul

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("dateformatted")
fun convertDateToText(textView: TextView, date: Date) {
    textView.text = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분").format(date)
}