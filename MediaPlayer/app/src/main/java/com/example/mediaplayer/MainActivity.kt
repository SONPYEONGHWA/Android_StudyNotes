package com.example.mediaplayer

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mediaplayer.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var galleryAdapter: GalleryAdapter
    private val viewModel: ImageViewModel by viewModels()
    val STORAGE_PERMISSION = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

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

    private fun getImages() {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()

        val galleryDataSource = DataSourceFactory(this.contentResolver)
        val galleryLiveData = LivePagedListBuilder(galleryDataSource, config).build()

        galleryLiveData.observe(this, Observer {
            galleryAdapter.submitList(it)
        })
    }

    companion object {
        private const val MULTIPLE_PERMISSION_CODE = 100
        private const val PICK_IMAGES_CODE = 300
    }
}

