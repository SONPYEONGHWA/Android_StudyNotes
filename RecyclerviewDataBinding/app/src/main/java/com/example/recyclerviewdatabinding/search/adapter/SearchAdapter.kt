package com.example.recyclerviewdatabinding.search.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdatabinding.BR
import com.example.recyclerviewdatabinding.databinding.ItemSearchResultBinding
import com.example.recyclerviewdatabinding.search.model.SearchResponse

class SearchAdapter(private val context: Context): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    var datas = mutableListOf<SearchResponse.Documents>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(position)

        holder.binding.constraintlayoutItem.setOnClickListener{
            val openUrl = Intent(Intent.ACTION_VIEW, Uri.parse(datas[position].url))
            ContextCompat.startActivity(context,openUrl, null)
        }
    }

    override fun getItemCount() = datas.size

    inner class SearchViewHolder(var binding: ItemSearchResultBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(position: Int) {
            binding.setVariable(BR.data, datas[position])
        }
    }
}