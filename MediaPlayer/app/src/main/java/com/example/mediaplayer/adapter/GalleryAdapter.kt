package com.example.mediaplayer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mediaplayer.BR
import com.example.mediaplayer.data.model.ImageModel
import com.example.mediaplayer.databinding.ItemImageBinding

class GalleryAdapter:
    PagedListAdapter<ImageModel, GalleryAdapter.GalleryViewHolder>(diffCallback){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = GalleryViewHolder(binding)

        return holder
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.setVariable(BR.data, item)
        holder.binding.root.setOnClickListener {
            itemClickListener.onClick(it, position)
            notifyItemChanged(position)
        }
    }

    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }

    private lateinit var itemClickListener: OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    inner class GalleryViewHolder(val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root)
}