package com.example.todo.model

import android.util.Log
import com.example.todo.api.TodoClient
import com.example.todo.contract.TodoListInterface
import com.example.todo.models.Todo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoListModel : TodoListInterface.ModelInterface {
    var todoList: List<Todo>? = null

    var presenter: TodoListInterface.PresenterInterface? = null
    override fun addValue() {}

    override fun getValue() {
        TodoClient.getTodos().enqueue(object : Callback<List<Todo>> {
            override fun onResponse(
                call: Call<List<Todo>>,
                response: Response<List<Todo>>
            ) {
                Log.e(Todo::class.java.simpleName, response.toString())
                todoList = response.body()
                updateList()
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                Log.e(Todo::class.java.simpleName, "error on get value", t)
            }
        })
    }


    override fun updateList() {
        presenter!!.getUpdatedData(todoList!!)
    }

    fun initData(presenter: TodoListInterface.PresenterInterface) {
        this.presenter = presenter
        getValue()
    }
}