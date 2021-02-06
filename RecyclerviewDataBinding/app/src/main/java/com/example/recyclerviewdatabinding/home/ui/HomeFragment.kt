package com.example.recyclerviewdatabinding.home.ui

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewdatabinding.databinding.FragmentHomeBinding
import com.example.recyclerviewdatabinding.home.viewmodel.DiaryViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: DiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(DiaryViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveDiary()
    }

    private fun saveDiary(){
        binding.buttonSave.setOnClickListener {
            viewModel.saveDiary(
                binding.edittextTitle, binding.edittextContents
            )
            clearEditText(binding.edittextTitle)
            clearEditText(binding.edittextContents)
        }
    }

    private fun clearEditText(editText: EditText) {
        editText.text.clear()
    }
}