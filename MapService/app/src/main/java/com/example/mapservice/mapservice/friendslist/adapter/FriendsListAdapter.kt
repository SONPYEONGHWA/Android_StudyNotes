package com.example.mapservice.mapservice.friendslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mapservice.databinding.ItemFriendsListBinding
import com.example.mapservice.mapservice.friendslist.model.ResponseFriendsList

class FriendsListAdapter: RecyclerView.Adapter<FriendsListAdapter.FriendsListViewHolder>() {

    val diffCallback = object : DiffUtil.ItemCallback<ResponseFriendsList.UserList>(){
        override fun areItemsTheSame(
            oldItem: ResponseFriendsList.UserList,
            newItem: ResponseFriendsList.UserList
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ResponseFriendsList.UserList,
            newItem: ResponseFriendsList.UserList
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<ResponseFriendsList.UserList>?) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsListViewHolder {
        val binding = ItemFriendsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendsListViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.setVariable(BR.model, item)
    }

    override fun getItemCount() = differ.currentList.size

    inner class FriendsListViewHolder(val binding: ItemFriendsListBinding): RecyclerView.ViewHolder(binding.root)
}