package com.example.mapservice.mapservice.websearch.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mapservice.BR
import com.example.mapservice.databinding.ItemSearchResultBinding
import com.example.mapservice.mapservice.websearch.model.SearchResponseModel

class SearchAdapter(val context: Context): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.setVariable(BR.model, item)
        clickItem(holder, position)
    }

    override fun getItemCount() = differ.currentList.size

    val diffCallback = object : DiffUtil.ItemCallback<SearchResponseModel.DocumentsModel>() {
        override fun areItemsTheSame(
            oldItem: SearchResponseModel.DocumentsModel,
            newItem: SearchResponseModel.DocumentsModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchResponseModel.DocumentsModel,
            newItem: SearchResponseModel.DocumentsModel
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<SearchResponseModel.DocumentsModel>?) = differ.submitList(list)

    fun clickItem(holder: SearchViewHolder, position:Int) {
        holder.binding.imageviewThumbnail.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(differ.currentList[position].url))
            ContextCompat.startActivity(context, intent, null)
        }
    }

    inner class SearchViewHolder(val binding: ItemSearchResultBinding): RecyclerView.ViewHolder(binding.root)
}