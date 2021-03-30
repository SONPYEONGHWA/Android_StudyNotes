package com.example.climateseoul.climateinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.climateseoul.climateinfo.adapter.ClimateInfoAdapter
import com.example.climateseoul.databinding.ActivityMainBinding
import com.example.climateseoul.util.Status
import com.example.climateseoul.util.VerticalItemDecoration
import com.example.climateseoul.climateinfo.viewmodel.ClimateInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ClimateInfoViewModel by viewModels()
    private lateinit var climateAdapter: ClimateInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        climateAdapter = ClimateInfoAdapter()
        binding.recyclerviweClimateInfo.apply {
            adapter = climateAdapter
            addItemDecoration(VerticalItemDecoration(50))
        }
        getClimateInfo()
    }

    private fun getClimateInfo() {
        viewModel.climateList.observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    binding.recyclerviweClimateInfo.visibility = View.VISIBLE
                    binding.progressbarClimateInfo.visibility = View.GONE
                    Log.e("success ", it.data?.IotVdata017?.row.toString())
                    climateAdapter.submitList(it.data?.IotVdata017?.row)
                }
                Status.LOADING -> {
                    binding.recyclerviweClimateInfo.visibility = View.GONE
                    binding.progressbarClimateInfo.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.recyclerviweClimateInfo.visibility = View.VISIBLE
                    binding.progressbarClimateInfo.visibility = View.GONE
                    Log.e("error fucking == ", it.message.toString())
                }
            }
        })
    }
}