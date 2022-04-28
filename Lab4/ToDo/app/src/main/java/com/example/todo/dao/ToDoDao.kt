package com.example.todo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.models.ToDo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAll(): List<ToDo>
    @Query("SELECT * FROM todo WHERE id=:id")
    fun getById(id: Int): ToDo
    @Insert()
    fun insert(todo: ToDo): Long
    @Update()
    fun update(todo: ToDo): Unit
}