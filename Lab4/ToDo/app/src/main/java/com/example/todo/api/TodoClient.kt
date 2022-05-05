package com.example.todo.api

import com.example.todo.models.Todo
import com.example.todolist.api.ApiService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TodoClient {
    private var BASE_URL: String = "https://jsonplaceholder.typicode.com/"
    private var apiService: ApiService

    init {
        val retrofit:Retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun getTodos(): Call<List<Todo>> {
        return apiService.getTodos()
    }

    fun getTodoById(todoId: Int): Call<Todo> {
        return apiService.getTodoById(todoId)
    }
}