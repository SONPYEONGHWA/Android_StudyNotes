package com.example.recyclerviewdatabinding.friendslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdatabinding.databinding.ItemDiaryTitleBinding
import com.example.recyclerviewdatabinding.home.model.DiaryModel

class DiaryListAdapter: RecyclerView.Adapter<DiaryListAdapter.FriendsViewHolder>() {
    var datas = listOf<DiaryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val binding = ItemDiaryTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    override fun getItemCount() = datas.size

    inner class FriendsViewHolder(val binding: ItemDiaryTitleBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(diaryModel: DiaryModel){
            binding.textviewTitle.text = diaryModel.title
        }
    }
}