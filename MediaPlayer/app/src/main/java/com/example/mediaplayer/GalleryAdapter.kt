package com.example.mediaplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mediaplayer.databinding.ItemImageBinding

class GalleryAdapter: RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    val diffCallback = object : DiffUtil.ItemCallback<ImageModel>() {
        override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<ImageModel>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.setVariable(BR.model, item)
    }

    override fun getItemCount() = differ.currentList.size

    inner class GalleryViewHolder(val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root)
}