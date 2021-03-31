package com.example.mapservice.mapservice.map.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mapservice.BR
import com.example.mapservice.databinding.ItemSearchAddressBinding
import com.example.mapservice.mapservice.map.model.LocationSearchResponse

class LocationBottomSheetAdapter(val listener: (LocationSearchResponse.Document) -> Unit): RecyclerView.Adapter<LocationBottomSheetAdapter.LocationBottomSheetViewHolder>() {

    val diffCallback = object : DiffUtil.ItemCallback<LocationSearchResponse.Document>(){
        override fun areItemsTheSame(oldItem: LocationSearchResponse.Document, newItem: LocationSearchResponse.Document): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LocationSearchResponse.Document, newItem: LocationSearchResponse.Document): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<LocationSearchResponse.Document>) = differ.submitList(list)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationBottomSheetViewHolder {
        val binding = ItemSearchAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationBottomSheetViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.setVariable(BR.model, item)

        holder.binding.constraintlayoutItemLocation.setOnClickListener {
            listener(item)
        }
    }

    override fun getItemCount() = differ.currentList.size

    inner class LocationBottomSheetViewHolder(val binding: ItemSearchAddressBinding): RecyclerView.ViewHolder(binding.root)
}