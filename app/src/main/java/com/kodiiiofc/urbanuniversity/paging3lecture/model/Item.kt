package com.kodiiiofc.urbanuniversity.paging3lecture.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
class Item(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)