package com.example.todo.presenter

import android.util.Log
import com.example.todo.contract.TodoListInterface
import com.example.todo.model.TodoListModel
import com.example.todo.models.Todo

class TodoListPresenter(_view: TodoListInterface.ViewInterface) : TodoListInterface.PresenterInterface {
    private var view: TodoListInterface.ViewInterface = _view
    private var model: TodoListModel = TodoListModel()
    private var todoList: List<Todo>? = null

    init {
        view.initView()
        model.initData(this)
    }

    override fun addValue() {
        model.addValue()
    }

    override fun getUpdatedData(value: List<Todo>) {
        Log.e("Response on Presenter", value.toString())
        todoList = value
        view.getDataFromPresenter(todoList!!)
    }
}