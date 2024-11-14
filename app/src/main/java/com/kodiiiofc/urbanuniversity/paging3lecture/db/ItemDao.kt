package com.kodiiiofc.urbanuniversity.paging3lecture.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kodiiiofc.urbanuniversity.paging3lecture.model.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM items ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPagedList(limit: Int, offset: Int) : List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item) : Long

}