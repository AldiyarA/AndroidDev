package com.example.todo.model

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
                todoList = response.body()
                updateList()
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
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