package com.example.rxjavasample.searchmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavasample.BR
import com.example.rxjavasample.databinding.ItemMovieBinding
import com.example.rxjavasample.searchmovie.remote.model.MovieResponseModel

class MovieListAdapter(val listener: (MovieResponseModel.Item) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    val diffCallback = object : DiffUtil.ItemCallback<MovieResponseModel.Item>() {
        override fun areItemsTheSame(
            oldItem: MovieResponseModel.Item,
            newItem: MovieResponseModel.Item
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: MovieResponseModel.Item,
            newItem: MovieResponseModel.Item
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<MovieResponseModel.Item>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.setVariable(BR.model, item)
        holder.binding.constraintlayoutItemMovie.setOnClickListener {
            listener(item)
        }
    }

    override fun getItemCount() = differ.currentList.size

    inner class MovieListViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)
}