package com.example.rxjavasample.searchmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavasample.BR
import com.example.rxjavasample.databinding.ItemSearchHistoryBinding
import com.example.rxjavasample.searchmovie.local.entity.SearchEntity
import com.example.rxjavasample.util.DiffUtil

class SearchHistoryAdapter: RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder>() {

    val diffUtil = DiffUtil<SearchEntity>()
    val differ = AsyncListDiffer(this, diffUtil)

    fun submitList(list: List<SearchEntity>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val binding = ItemSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.setVariable(BR.model, item)
        holder.binding.imageviewDelete.setOnClickListener {

        }
    }

    override fun getItemCount() = differ.currentList.size

    inner class SearchHistoryViewHolder(val binding: ItemSearchHistoryBinding): RecyclerView.ViewHolder(binding.root)
}