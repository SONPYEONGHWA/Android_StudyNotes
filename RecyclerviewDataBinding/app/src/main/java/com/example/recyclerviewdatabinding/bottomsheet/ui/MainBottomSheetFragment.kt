package com.example.recyclerviewdatabinding.bottomsheet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.recyclerviewdatabinding.R
import com.example.recyclerviewdatabinding.bottomsheet.adapter.MainBottomSheetAdapter
import com.example.recyclerviewdatabinding.bottomsheet.moel.MainBottomSheetModel
import com.example.recyclerviewdatabinding.bottomsheet.viewmodel.MainBottomSheetViewModel
import com.example.recyclerviewdatabinding.databinding.FragmentMainBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MainBottomSheetFragment() : BottomSheetDialogFragment() {
    private val viewModel: MainBottomSheetViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBottomSheetBinding
    private lateinit var adapter: MainBottomSheetAdapter
    private var datas = mutableListOf<MainBottomSheetModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()

    }

    private fun setRecyclerView() {
        adapter = MainBottomSheetAdapter()
        binding.recyclerviewBottomsheet.adapter = adapter
        loadDatas()
    }

    private fun loadDatas() {
        datas = mutableListOf(
            MainBottomSheetModel("peace", 26),
            MainBottomSheetModel("ju_yae", 24),
            MainBottomSheetModel("sopt", 27),
            MainBottomSheetModel("peace", 26),
            MainBottomSheetModel("peace", 26)
        )

        viewModel.changeUserList(datas as ArrayList<MainBottomSheetModel>)

        viewModel.userList.observe(viewLifecycleOwner, {
            adapter.datas = it
            adapter.notifyDataSetChanged()
        })
    }
}