package com.example.mapservice.mapservice.friendslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mapservice.databinding.FragmentFriendsListBinding
import com.example.mapservice.mapservice.friendslist.viewmodel.FriendsListViewModel
import com.example.mapservice.mapservice.friendslist.adapter.FriendsListAdapter
import com.example.mapservice.mapservice.utils.DataState
import com.example.mapservice.mapservice.utils.VerticalItemDecoration
import com.google.android.material.snackbar.Snackbar

class FriendsListFragment : Fragment() {
    private lateinit var binding: FragmentFriendsListBinding
    private val viewModel: FriendsListViewModel by activityViewModels()
    private lateinit var adapter: FriendsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }
    private fun initRecyclerView() {
        adapter = FriendsListAdapter()
        binding.recyclerviewFriendsList.adapter = adapter
        binding.recyclerviewFriendsList.addItemDecoration(VerticalItemDecoration(15))
        initFriendsList()
    }


    private fun initFriendsList() {
        viewModel.friendsList.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                DataState.Status.SUCCESS -> {
                    binding.progressbarFriendsList.visibility = View.GONE
                    binding.recyclerviewFriendsList.visibility = View.VISIBLE
                    adapter.submitList(it.data?.userList)
                }
                DataState.Status.LOADING -> {
                    binding.progressbarFriendsList.visibility = View.VISIBLE
                    binding.recyclerviewFriendsList.visibility = View.GONE
                }
                DataState.Status.SUCCESS -> {
                    binding.progressbarFriendsList.visibility = View.GONE
                    binding.recyclerviewFriendsList.visibility = View.VISIBLE
                    Snackbar.make(binding.root, "친구목록 불러오기 실패", Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }
}