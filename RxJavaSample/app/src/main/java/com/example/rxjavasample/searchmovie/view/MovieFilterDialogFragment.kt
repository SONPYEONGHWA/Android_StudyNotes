package com.example.rxjavasample.searchmovie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.rxjavasample.databinding.FragmentMovieFilterDialogBinding
import com.example.rxjavasample.searchmovie.model.Country
import com.example.rxjavasample.searchmovie.viewmodel.SearchMovieViewModel
import com.google.android.material.chip.Chip

class MovieFilterDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentMovieFilterDialogBinding
    private val viewModel: SearchMovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieFilterDialogBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chipGroupCountry.children.forEach {
            (it as Chip).setOnCheckedChangeListener { buttonView, isChecked ->
                handleSelection()
            }
        }
        dismissDialog()
    }

    private fun handleSelection() {
        binding.chipGroupCountry.checkedChipIds.forEach {
            when(it) {
                binding.chipKorea.id -> viewModel.changeCountryFilter(Country.KOREA.code)
                binding.chipBritish.id -> viewModel.changeCountryFilter(Country.GREAT_BRITISH.code)
                binding.chipFrance.id -> viewModel.changeCountryFilter(Country.FRANCE.code)
                binding.chipHongkong.id -> viewModel.changeCountryFilter(Country.HONGKONG.code)
                binding.chipJapan.id -> viewModel.changeCountryFilter(Country.JAPAN.code)
                binding.chipUs.id -> viewModel.changeCountryFilter(Country.UNITED_STATE.code)
                binding.chipEtc.id -> viewModel.changeCountryFilter(Country.ETC.code)
            }
        }
    }
    private fun dismissDialog() {
        binding.buttonDismiss.setOnClickListener {
            dismiss()
        }
    }
}