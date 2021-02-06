package com.example.recyclerviewdatabinding.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.recyclerviewdatabinding.friendslist.ui.DiaryListFragment
import com.example.recyclerviewdatabinding.home.ui.HomeFragment
import com.example.recyclerviewdatabinding.search.ui.SearchFragment

class ViewPagerAdapater(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0-> HomeFragment()
            1-> DiaryListFragment()
            else -> SearchFragment()
        }
    }

    override fun getCount() = MAX_PAGE_COUNT

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Home"
            1 -> "DiaryList"
            else -> "Search"
        }
    }

    companion object {
        private val MAX_PAGE_COUNT = 3
    }
}