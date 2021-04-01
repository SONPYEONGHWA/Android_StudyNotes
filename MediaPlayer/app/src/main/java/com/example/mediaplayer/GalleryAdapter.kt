package com.example.mediaplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mediaplayer.databinding.ItemImageBinding

class GalleryAdapter: PagedListAdapter<ImageModel, GalleryAdapter.GalleryViewHolder>(diffCallback) {
    val images = mutableListOf<ImageModel>()

    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<ImageModel>(){
            override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val image = getItem(position)
        holder.onBind(image)
    }

    inner class GalleryViewHolder(val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(imageModel: ImageModel?) {
            Glide.with(binding.root.context).load(imageModel?.imageDataPath).into(binding.imageviewPicture)
        }
    }
}