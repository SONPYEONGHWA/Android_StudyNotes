package com.example.recyclerviewdatabinding.friendslist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewdatabinding.databinding.FragmentDiaryListBinding
import com.example.recyclerviewdatabinding.friendslist.adapter.DiaryListAdapter
import com.example.recyclerviewdatabinding.home.viewmodel.DiaryViewModel
import com.example.recyclerviewdatabinding.utils.VerticalItemDecoration

class DiaryListFragment : Fragment() {
    private lateinit var binding: FragmentDiaryListBinding
    private lateinit var viewModel: DiaryViewModel
    private lateinit var adapter: DiaryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiaryListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(DiaryViewModel::class.java)
        initRecyclerView()
    }

    fun initRecyclerView() {
        adapter = DiaryListAdapter()
        binding.recyclerviewDiaryList.adapter = adapter
        binding.recyclerviewDiaryList.addItemDecoration(VerticalItemDecoration(5))
        getDiaryList()
    }

    fun getDiaryList(){
        viewModel.getAll().observe(viewLifecycleOwner, Observer {
            adapter.datas = it
            adapter.notifyDataSetChanged()
        })
    }
}