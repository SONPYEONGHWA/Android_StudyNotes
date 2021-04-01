package com.example.mediaplayer.data.source

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.paging.PositionalDataSource
import com.example.mediaplayer.data.model.ImageModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class GalleryDataSource @Inject constructor(
    private val contentResolver: ContentResolver
    ): PositionalDataSource<ImageModel>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<ImageModel>) {
        callback.onResult(getImages(params.requestedLoadSize, params.requestedStartPosition),0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<ImageModel>) {
        callback.onResult(getImages(params.loadSize, params.startPosition))
    }

    private fun getImages(limit: Int, offset: Int): MutableList<ImageModel> {
        val images = mutableListOf<ImageModel>()

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_TAKEN
        )

        val selection = "${MediaStore.Images.Media.DATE_TAKEN} >= ?"
        val selectionArgs = arrayOf(
            dateToTimestamp(
            day = 1, month = 1, year = 1970
        ).toString()
        )

        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )

        cursor?.use {
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val dateTakenColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

            while (cursor.moveToNext()){
                val id = cursor.getLong(idColumn)
                val dateTaken = Date(cursor.getLong(dateTakenColumn))
                val displayName = cursor.getString(displayNameColumn)
                val contentUri = Uri.withAppendedPath(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id.toString()
                )
                images.add(ImageModel(contentUri, id))
            }
        }
        return images
    }

    private fun dateToTimestamp(day: Int, month: Int, year: Int): Long =
        SimpleDateFormat("dd.MM.yyyy").let { formatter ->
            formatter.parse("$day.$month.$year")?.time ?: 0
        }
}