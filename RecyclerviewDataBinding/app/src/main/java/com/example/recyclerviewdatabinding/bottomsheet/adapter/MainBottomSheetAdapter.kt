package com.example.recyclerviewdatabinding.bottomsheet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdatabinding.bottomsheet.moel.MainBottomSheetModel
import com.example.recyclerviewdatabinding.databinding.ItemBottomSheetBinding

class MainBottomSheetAdapter : RecyclerView.Adapter<MainBottomSheetAdapter.MainBottomSheetViewHolder>() {
    var datas = mutableListOf<MainBottomSheetModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainBottomSheetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBottomSheetBinding.inflate(inflater, parent, false)
        return MainBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainBottomSheetViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount() = datas.size

    inner class MainBottomSheetViewHolder(private val binding: ItemBottomSheetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mainBottomSheetModel: MainBottomSheetModel) {
            binding.data = mainBottomSheetModel
        }
    }
}