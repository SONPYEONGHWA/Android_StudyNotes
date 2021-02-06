package com.example.recyclerviewdatabinding.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewdatabinding.utils.ViewPagerAdapater
import com.example.recyclerviewdatabinding.bottomsheet.ui.MainBottomSheetFragment
import com.example.recyclerviewdatabinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val bottomSheetFragment = MainBottomSheetFragment()
    private val pagerAdapter by lazy { ViewPagerAdapater(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        binding.lifecycleOwner = this
        setContentView(binding.root)

        initViewPager()

    }

    private fun initViewPager() {
        binding.viewpager.adapter = pagerAdapter
        binding.tablayoutMain.setupWithViewPager(binding.viewpager)
    }
//
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        when (event.action) {
//            MotionEvent.ACTION_UP -> {
//                bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
//            }
//        }
//        return true
//    }

//    fun setFragmentNavigation(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment, fragment).commitAllowingStateLoss()
//    }
}