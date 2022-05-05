
package com.example.todolist.api

import com.example.todo.models.Todo
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("todos/")
    fun getTodos(): Call<List<Todo>>

    @GET("todos/{id}/")
    fun getTodoById(@Path("id") todoId: Int): Call<Todo>
}
