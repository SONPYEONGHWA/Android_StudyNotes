package com.example.mediaplayer.data.source

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.net.Uri.withAppendedPath
import android.provider.MediaStore
import android.util.Log
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.PositionalDataSource
import com.example.mediaplayer.data.model.ImageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GalleryDataSource @Inject constructor(
    private val contentResolver: ContentResolver
    ): PagingSource<Int, ImageModel>() {
    private val images = mutableListOf<ImageModel>()

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        return try {
            val pagedKeyDate = params.key ?: System.currentTimeMillis()
            val images = getImages()
            val lastItemDate = images.last().dateTaken

            if (pagedKeyDate == lastItemDate) {
                return LoadResult.Error(Throwable())
            }

            LoadResult.Page(
                data = images,
                prevKey = null,
                nextKey = images.last().id.toInt()
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun getImages() = withContext(Dispatchers.IO) {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )

//        val selection = "${MediaStore.Images.Media.DATE_TAKEN} >= ?"
//
//        val selectionArgs = arrayOf(
//            dateToTimestamp(
//            day = 1, month = 1, year = 1970
//            ).toString()
//        )

        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )

        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(projection[0])
            val displayNameColumn = it.getColumnIndexOrThrow(projection[1])
            val dateTakenColumn = it.getColumnIndexOrThrow(projection[2])
            val bucketDisplayName = it.getColumnIndexOrThrow(projection[3])

            while (it.moveToNext()){
                val id = it.getLong(idColumn)
                val dateTaken = it.getLong(dateTakenColumn)
                val displayName = it.getString(displayNameColumn)
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                val bucketId = it.getString(bucketDisplayName)

                Log.d("images", "id : $id, contentUri: $contentUri, diplayName: $displayName, folder: $bucketId")
                images.add(ImageModel(contentUri, dateTaken, displayName, id, bucketDisplayName.toString()))
            }
        }
        cursor?.close()
        images
    }

    private fun dateToTimestamp(day: Int, month: Int, year: Int): Long =
        SimpleDateFormat("dd.MM.yyyy").let { formatter ->
            formatter.parse("$day.$month.$year")?.time ?: 0
        }
}