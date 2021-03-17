package com.example.mapservice.mapservice.map

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mapservice.BR
import com.example.mapservice.databinding.ItemSearchAddressBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class LocationBottomSheetAdapter(val listener: (AddressModel) -> Unit): RecyclerView.Adapter<LocationBottomSheetAdapter.LocationBottomSheetViewHolder>() {

    val diffCallback = object : DiffUtil.ItemCallback<AddressModel>(){
        override fun areItemsTheSame(oldItem: AddressModel, newItem: AddressModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AddressModel, newItem: AddressModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<AddressModel>) = differ.submitList(list)

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