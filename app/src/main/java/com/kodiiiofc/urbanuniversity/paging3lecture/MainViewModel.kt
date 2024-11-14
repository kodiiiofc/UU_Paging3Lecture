package com.kodiiiofc.urbanuniversity.paging3lecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.kodiiiofc.urbanuniversity.paging3lecture.db.ItemDao
import com.kodiiiofc.urbanuniversity.paging3lecture.pagination.MainPagingSource

class MainViewModel(
    private val dao: ItemDao
) : ViewModel() {

    val data = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
    ) {
        MainPagingSource(dao)
    }.flow.cachedIn(viewModelScope)


}

