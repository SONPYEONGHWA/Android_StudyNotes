package com.example.daggerhiltstudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerhiltstudy.BR
import com.example.daggerhiltstudy.databinding.ItemProfileBinding
import com.example.daggerhiltstudy.model.UsersModel

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.setVariable(BR.model, item)
    }

    override fun getItemCount() = differ.currentList.size


    val diffCallback = object: DiffUtil.ItemCallback<UsersModel>(){
        override fun areItemsTheSame(oldItem: UsersModel, newItem: UsersModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UsersModel, newItem: UsersModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<UsersModel>?) = differ.submitList(list)

    inner class UserListViewHolder(val binding: ItemProfileBinding): RecyclerView.ViewHolder(binding.root)
}