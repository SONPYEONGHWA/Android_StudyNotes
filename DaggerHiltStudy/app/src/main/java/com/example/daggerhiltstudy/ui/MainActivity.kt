package com.example.daggerhiltstudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.daggerhiltstudy.model.DataState
import com.example.daggerhiltstudy.adapter.UserListAdapter
import com.example.daggerhiltstudy.databinding.ActivityMainBinding
import com.example.daggerhiltstudy.utils.VerticalItemDecoration
import com.example.daggerhiltstudy.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userListadapter: UserListAdapter
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        userListadapter = UserListAdapter()
        binding.recyclerviewProfile.adapter = userListadapter
        binding.recyclerviewProfile.addItemDecoration(VerticalItemDecoration(10))
        loadUserList()
    }

    private fun loadUserList() {
        viewModel.userList.observe(this, Observer {
            when(it.status) {
                DataState.Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.recyclerviewProfile.visibility = View.VISIBLE
                    userListadapter.submitList(it.data)
                }

                DataState.Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.recyclerviewProfile.visibility = View.GONE
                }

                DataState.Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.recyclerviewProfile.visibility = View.VISIBLE
                    Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_SHORT ).show()
                }
            }
        })
    }
}