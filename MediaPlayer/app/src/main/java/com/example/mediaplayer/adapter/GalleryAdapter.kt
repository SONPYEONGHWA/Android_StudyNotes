package com.example.mediaplayer.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mediaplayer.BR
import com.example.mediaplayer.data.model.ImageModel
import com.example.mediaplayer.databinding.ItemImageBinding
import java.util.*

//Todo: notifyItemChanged에 payload를 넣어서 원하는 부분만 갱신되도록 변경
class GalleryAdapter: PagingDataAdapter<ImageModel, GalleryAdapter.GalleryViewHolder>(diffCallback){
    private val checkboxStatus = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            setVariable(BR.data, item)
            checkboxSelect.isChecked = checkboxStatus[position]

            checkboxSelect.setOnClickListener {
                checkboxStatus.put(position, checkboxSelect.isChecked)
                notifyItemChanged(position)
            }
        }

    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ImageModel>() {
            override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class GalleryViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)
}