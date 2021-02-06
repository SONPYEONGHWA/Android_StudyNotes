package com.example.recyclerviewdatabinding.home.viewmodel

import android.app.Application
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.recyclerviewdatabinding.home.model.DiaryModel
import com.example.recyclerviewdatabinding.home.repository.DiaryRepository

class DiaryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DiaryRepository(application)
    private val diaryDao = repository.getAll()

    fun insert(diaryModel: DiaryModel) {
        repository.insert(diaryModel)
    }

    fun getAll(): LiveData<List<DiaryModel>> {
        return diaryDao
    }

    fun delete(diaryModel: DiaryModel) {
        repository.delete(diaryModel)
    }

    fun saveDiary(title: EditText, contents: EditText) {
        if (!(title.text.isNullOrBlank() || contents.text.isNullOrBlank())) {
            insert(DiaryModel(null, title.text.toString(), contents.text.toString()))
        } else {
            Toast.makeText(getApplication(), "내용을 채워주세요", Toast.LENGTH_SHORT).show()
        }
    }
}