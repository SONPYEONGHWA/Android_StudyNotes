package com.example.mapnaverapi.map.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mapnaverapi.BR
import com.example.mapnaverapi.databinding.ItemLocationListBinding
import com.example.mapnaverapi.map.model.AddressModel

class LocationListAdapter(val listener: (AddressModel) -> Unit): RecyclerView.Adapter<LocationListAdapter.LocationListViewHolder>() {

    val diffCallback = object : DiffUtil.ItemCallback<AddressModel>(){
        override fun areItemsTheSame(oldItem: AddressModel, newItem: AddressModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AddressModel, newItem: AddressModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<AddressModel>?) = differ.submitList(list)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationListViewHolder {
        val binding = ItemLocationListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationListViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.setVariable(BR.model, item)
        holder.binding.constraintlayoutItemLocation.setOnClickListener {
            listener(item)
        }
    }

    override fun getItemCount() = differ.currentList.size

    inner class LocationListViewHolder(val binding: ItemLocationListBinding): RecyclerView.ViewHolder(binding.root)
}