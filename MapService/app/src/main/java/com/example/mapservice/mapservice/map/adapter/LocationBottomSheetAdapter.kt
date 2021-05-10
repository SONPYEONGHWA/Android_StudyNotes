package com.example.mapservice.mapservice.map.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mapservice.BR
import com.example.mapservice.databinding.ItemSearchAddressBinding
import com.example.mapservice.mapservice.map.data.entity.LocationEntity

class LocationBottomSheetAdapter(val listener: (LocationEntity) -> Unit): ListAdapter<LocationEntity, LocationBottomSheetAdapter.LocationBottomSheetViewHolder>(
    diffCallback
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationBottomSheetViewHolder {
        val binding = ItemSearchAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationBottomSheetViewHolder, position: Int) {
        val item = currentList[position]
        holder.binding.setVariable(BR.model, item)

        holder.binding.constraintlayoutItemLocation.setOnClickListener {
            listener(item)
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<LocationEntity>(){
            override fun areItemsTheSame(oldItem: LocationEntity, newItem: LocationEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: LocationEntity, newItem: LocationEntity): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }

    class LocationBottomSheetViewHolder(val binding: ItemSearchAddressBinding): RecyclerView.ViewHolder(binding.root)
}