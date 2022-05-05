package com.example.todo.model

import android.util.Log
import com.example.todo.api.TodoClient
import com.example.todo.contract.TodoDetailInterface
import com.example.todo.models.Todo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoDetailModel(var todoId: Int) : TodoDetailInterface.ModelInterface {
    private var todo: Todo? = null

    private var presenter: TodoDetailInterface.PresenterInterface? = null

    override fun getValue() {
        TodoClient.getTodoById(todoId).enqueue(object : Callback<Todo> {
            override fun onResponse(
                call: Call<Todo>,
                response: Response<Todo>
            ) {
                Log.e(Todo::class.java.simpleName, response.toString())
                todo = response.body()
                updateValue()
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                Log.e(Todo::class.java.simpleName, "error on get value", t)
            }
        })
    }

    override fun updateValue() {
        presenter!!.getUpdatedData(todo!!)
    }

    fun initData(presenter: TodoDetailInterface.PresenterInterface) {
        this.presenter = presenter
        getValue()
    }
}