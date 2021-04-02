package com.example.mediaplayer.view

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Selection
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mediaplayer.*
import com.example.mediaplayer.adapter.GalleryAdapter
import com.example.mediaplayer.data.model.ImageModel
import com.example.mediaplayer.databinding.ActivityMainBinding
import com.example.mediaplayer.utils.HorizontalItemDecoration
import com.example.mediaplayer.utils.SelectionManager
import com.example.mediaplayer.utils.VerticalItemDecoration
import com.example.mediaplayer.viewModel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity: AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var galleryAdapter: GalleryAdapter
    private val viewModel: ImageViewModel by viewModels()
    private val selectionManager = SelectionManager()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initRecyclerview()
        selectPictures()
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

    private fun selectPictures() {
        if (checkPermission(STORAGE_PERMISSION, MULTIPLE_PERMISSION_CODE)) {
            getImages()
        }
    }

    private fun getImages() {
        viewModel.galleryLiveData.observe(this, Observer { list ->
            galleryAdapter.submitList(list)
            viewModel.changeImageList(list)
        })

        galleryAdapter.setItemClickListener(object : GalleryAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val list = viewModel.imageList.value!!
                val image = list[position]
//                selectionManager.toggle(image)
                image!!.isSelected = !image!!.isSelected
                Log.e("image", image.isSelected.toString())

            }
        })
    }

    //권한처리 메서드
    private fun checkPermission(permissions: Array<out String>, flag: Int): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(permissions, flag)
                    return false
                }
            }
        }
        return true
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            MULTIPLE_PERMISSION_CODE -> {
                for (grant in grantResults) {
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "저장소 권한을 승인해야 프로필 사진을 설정할 수 있습니다", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }
                }
            }
        }
    }

    companion object {
        private const val MULTIPLE_PERMISSION_CODE = 100
        val STORAGE_PERMISSION = arrayOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }
}

