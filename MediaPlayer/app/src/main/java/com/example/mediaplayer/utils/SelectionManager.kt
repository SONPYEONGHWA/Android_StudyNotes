package com.example.mediaplayer.utils

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.mediaplayer.data.model.ImageModel


class SelectionManager: BaseObservable() {
    val selectedMediaMap = LinkedHashMap<Long, ImageModel>()

    fun setChecked(image: ImageModel, checked: Boolean){
        if (checked) {
            selectedMediaMap[image.id] = image
        } else {
            selectedMediaMap.remove(image.id)
        }
    }

    fun isChecked(id: Long) = selectedMediaMap.containsKey(id)

    fun toggle(image: ImageModel) {
        setChecked(image, !isChecked(image.id))
    }
}