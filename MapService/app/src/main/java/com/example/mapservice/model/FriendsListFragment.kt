package com.example.mapservice.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mapservice.R
import com.example.mapservice.databinding.FragmentFriendsListBinding

class FriendsListFragment : Fragment() {
    private lateinit var binding: FragmentFriendsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToMyPage()
    }

    private fun goToMyPage(){
        val navController = Navigation.findNavController(binding.root)

        binding.buttonNext.setOnClickListener {
            navController.navigate(R.id.action_friendsListFragment_to_myPageFragment)
        }
    }
}