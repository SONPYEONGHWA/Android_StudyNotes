package com.example.mediaplayer.adapter

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mediaplayer.BR
import com.example.mediaplayer.R
import com.example.mediaplayer.data.model.ImageModel
import com.example.mediaplayer.databinding.ItemImageBinding
import java.util.*

//Todo: notifyItemChanged에 payload를 넣어서 원하는 부분만 갱신되도록 변경
class GalleryAdapter(val listener: (ImageModel) -> Unit):
    PagingDataAdapter<ImageModel, GalleryAdapter.GalleryViewHolder>(diffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.setVariable(BR.data, item)
        holder.binding.root.setOnClickListener {
            listener(item!!)
//            itemClickListener.onClick(it, position)
            notifyItemChanged(position)
        }
    }

//    override fun onBindViewHolder(
//        holder: GalleryViewHolder,
//        position: Int,
//        payloads: MutableList<Any>
//    ) {
//        if (payloads.isEmpty()) {
//            super.onBindViewHolder(holder, position, payloads)
//        } else {
//                changeItemViewState(holder)
//        }
//    }

    fun changeItemViewState(holder: GalleryViewHolder) {
        holder.binding.run {
            if (data!!.isSelected) {
                imageviewPicture.setColorFilter(R.color.gray, PorterDuff.Mode.XOR)
                imageviewSelect.visibility = View.VISIBLE
            } else {
                imageviewPicture.clearColorFilter()
                imageviewSelect.visibility = View.GONE
            }
        }
    }

//    interface OnItemClickListener{
//        fun onClick(v: View, position: Int)
//    }
//
//    private lateinit var itemClickListener: OnItemClickListener
//
//    fun setItemClickListener(itemClickListener: OnItemClickListener) {
//        this.itemClickListener = itemClickListener
//    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ImageModel>() {
            override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return oldItem == newItem
            }
        }
//        val SELECT_PAYLOAD = "SELECT_PAYLOAD"
    }

    inner class GalleryViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)
}