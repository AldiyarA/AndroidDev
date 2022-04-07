package com.example.todo
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.dao.TodoDao
import com.example.todo.models.Category
import com.example.todo.models.ToDo

@Database(
    entities = [ToDo::class, Category::class],
    version = 2,
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}