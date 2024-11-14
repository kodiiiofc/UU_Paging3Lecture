package com.kodiiiofc.urbanuniversity.paging3lecture.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kodiiiofc.urbanuniversity.paging3lecture.db.ItemDao
import com.kodiiiofc.urbanuniversity.paging3lecture.model.Item
import kotlinx.coroutines.delay

class MainPagingSource (
    private val dao : ItemDao
) : PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        } as Int?
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val page = params.key ?: 0
        return try {
            val entities = dao.getPagedList(
                limit = params.loadSize,
                offset = page * params.loadSize
            )
            if (page != 0) delay(1000)
            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}