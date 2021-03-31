package com.example.mediaplayer

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.mediaplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GalleryAdapter
    private val viewModel: ImageViewModel by viewModels()
    private var images: MutableList<ImageModel>? = null
    val STORAGE_PERMISSION = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initRecyclerview()
        selectPictures()
        updateGallery()
    }

    private fun initRecyclerview() {
        images = mutableListOf()
        adapter = GalleryAdapter()
        binding.recyclerviewImages.adapter = adapter
        binding.recyclerviewImages.addItemDecoration(HorizontalItemDecoration(10))
    }

    private fun selectPictures() {
        binding.buttonPickImages.setOnClickListener {
            if (checkPermission(STORAGE_PERMISSION, MULTIPLE_PERMISSION_CODE)) {
                images = mutableListOf()
                pickImages()
            }
        }
    }
    //권한처리 메서드
    private fun checkPermission(permissions: Array<out String>, flag: Int) :Boolean {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            for(permission in permissions) {
                if(ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
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
                for (grant in grantResults){
                    if(grant != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "저장소 권한을 승인해야 프로필 사진을 설정할 수 있습니다", Toast.LENGTH_SHORT).show()
                        return
                    }
                }
                pickImages()
            }
        }
    }

    private fun pickImages() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Select Images"), PICK_IMAGES_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGES_CODE) {
            if (resultCode == RESULT_OK) {
                if(data!!.clipData != null) {
                    val count = data.clipData!!.itemCount
                    for (i in 0 until count) {
                        val imageUri = data.clipData!!.getItemAt(i).uri
                        images!!.add(ImageModel(imageUri))
                    }
                    Log.e("images ", images.toString())
                    viewModel.changeImages(images as List<ImageModel>)
                } else {
                    val imageUri = data.data
                    images!!.add(ImageModel(imageUri!!))
                    viewModel.changeImages(images as List<ImageModel>)
                }
            }
        }
    }

    private fun updateGallery() {
        viewModel.images.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    companion object{
        private const val MULTIPLE_PERMISSION_CODE = 100
        private const val PICK_IMAGES_CODE = 300
    }
}