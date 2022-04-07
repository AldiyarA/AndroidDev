package com.example.todo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "status") var status: Boolean? = null,
    @ColumnInfo(name = "categoryId") var categoryId: Int? = null,
    @ColumnInfo(name = "duration") var duration: String? = null,

    ) {
}