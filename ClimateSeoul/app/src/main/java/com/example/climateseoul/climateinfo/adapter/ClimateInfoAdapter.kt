package com.example.climateseoul.climateinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.climateseoul.BR
import com.example.climateseoul.climateinfo.model.IotVdata
import com.example.climateseoul.databinding.ItemClimateInfoBinding

class ClimateInfoAdapter: RecyclerView.Adapter<ClimateInfoAdapter.ClimateViewHolder>() {

    val differCallback = object : DiffUtil.ItemCallback<IotVdata.Row>(){
        override fun areItemsTheSame(
            oldItem: IotVdata.Row,
            newItem: IotVdata.Row
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: IotVdata.Row,
            newItem: IotVdata.Row
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    fun submitList(list: List<IotVdata.Row>?) = differ.submitList(list)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClimateViewHolder {
        val binding = ItemClimateInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClimateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClimateViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.setVariable(BR.model, item)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ClimateViewHolder(val binding: ItemClimateInfoBinding): RecyclerView.ViewHolder(binding.root)
}