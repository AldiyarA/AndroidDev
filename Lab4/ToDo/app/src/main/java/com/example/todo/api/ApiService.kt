
package com.example.todolist.api

import com.example.todo.models.ToDo
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {
    @GET("todos/")
    fun getTodos(): Call<List<ToDo>>

    @GET("todos/{id}/")
    fun getTodoById(@Path("id") todoId: Int): Call<ToDo>
}

fun createApiService(): ApiService {
    val url = "https://jsonplaceholder.typicode.com/"
    val client = OkHttpClient.Builder()
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}