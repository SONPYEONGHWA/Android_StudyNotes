package com.example.climateseoul.util

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class VerticalItemDecoration(val divHeight: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        outRect.top = divHeight
        outRect.bottom = divHeight
    }
}