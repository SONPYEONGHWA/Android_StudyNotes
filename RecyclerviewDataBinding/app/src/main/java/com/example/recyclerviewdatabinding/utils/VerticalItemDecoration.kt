package com.example.recyclerviewdatabinding.utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class VerticalItemDecoration(var divHeight: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        outRect.top = divHeight
        outRect.bottom = divHeight
    }
}