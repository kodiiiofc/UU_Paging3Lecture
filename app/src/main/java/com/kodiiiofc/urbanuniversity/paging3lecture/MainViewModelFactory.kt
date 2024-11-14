package com.kodiiiofc.urbanuniversity.paging3lecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodiiiofc.urbanuniversity.paging3lecture.db.ItemDao

class MainViewModelFactory(
    private val dao: ItemDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Неизвестный класс ViewModel")
    }

}