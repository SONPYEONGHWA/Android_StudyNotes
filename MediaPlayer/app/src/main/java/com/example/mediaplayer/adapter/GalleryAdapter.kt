package com.example.mediaplayer.adapter

import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mediaplayer.BR
import com.example.mediaplayer.R
import com.example.mediaplayer.data.model.ImageModel
import com.example.mediaplayer.data.model.SelectedImageModel
import com.example.mediaplayer.databinding.ItemImageBinding
import javax.inject.Inject

class GalleryAdapter : PagedListAdapter<ImageModel, GalleryAdapter.GalleryViewHolder>(diffCallback) {
    private val checkImageList = mutableListOf<Long>()

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
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        getItem(position).let { item ->
            with(holder) {
                binding.setVariable(BR.data, item)
                binding.cardviewPicture.setOnClickListener{
                    if (checkImageList.contains(item?.id)) {
                        checkImageList.remove(item?.id)
                        holder.binding.imageviewSelect.visibility = View.GONE
                        holder.binding.imageviewPicture.clearColorFilter()
                    } else {
                        checkImageList.add(item?.id!!)
                        holder.binding.imageviewSelect.visibility = View.VISIBLE
                        holder.binding.imageviewPicture.setColorFilter(R.color.gray, PorterDuff.Mode.XOR)
                    }

                    Log.e("images ", checkImageList.toString() )
                }
            }
        }
    }

    override fun onCurrentListChanged(
        previousList: PagedList<ImageModel>?,
        currentList: PagedList<ImageModel>?
    ) {
        super.onCurrentListChanged(previousList, currentList)
    }


    inner class GalleryViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)
}