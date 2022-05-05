package com.example.todo.contract

import com.example.todo.models.Todo

interface TodoListInterface {

    interface ViewInterface {
        fun initView()
        fun getDataFromPresenter(value: List<Todo>)
    }

    interface PresenterInterface {
        fun addValue()
        fun getUpdatedData(value: List<Todo>)
    }

    interface ModelInterface {
        fun addValue()
        fun getValue()
        fun updateList()
    }
}