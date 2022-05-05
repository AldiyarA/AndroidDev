package com.example.todo.contract

import com.example.todo.models.Todo

interface TodoDetailInterface {

    interface ViewInterface {
        fun getDataFromPresenter(value: Todo)
    }

    interface PresenterInterface {
        fun getUpdatedData(value: Todo)
    }

    interface ModelInterface {
        fun getValue()
        fun updateValue()
    }
}