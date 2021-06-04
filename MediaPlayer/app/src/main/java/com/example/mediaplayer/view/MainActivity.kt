package com.example.mediaplayer.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mediaplayer.*
import com.example.mediaplayer.adapter.GalleryAdapter
import com.example.mediaplayer.databinding.ActivityMainBinding
import com.example.mediaplayer.utils.HorizontalItemDecoration
import com.example.mediaplayer.utils.VerticalItemDecoration
import com.example.mediaplayer.viewModel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MainActivity: AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var galleryAdapter: GalleryAdapter
    private val viewModel: ImageViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initRecyclerview()
        selectPictures.launch(STORAGE_PERMISSION)
    }

    private fun initRecyclerview() {
        galleryAdapter = GalleryAdapter()

        binding.recyclerviewImages.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            addItemDecoration(HorizontalItemDecoration(10))
            addItemDecoration(VerticalItemDecoration(10))
            adapter = galleryAdapter
        }
    }

    private val selectPictures = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permissions ->
            if(permissions.values.filter { it == false }.count() == 0) {
                getImages()
            } else {
                Toast.makeText(this, "권한을 모두 허용해주세요", Toast.LENGTH_SHORT).show()
            }
        }

    private fun getImages() {
        lifecycleScope.launch {
            viewModel.galleryLiveData().collectLatest{ pagingData ->
                galleryAdapter.submitData(pagingData)
                viewModel.changeImageList(galleryAdapter.snapshot().items)
            }
        }
    }

    companion object {
        val STORAGE_PERMISSION = arrayOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }
}

