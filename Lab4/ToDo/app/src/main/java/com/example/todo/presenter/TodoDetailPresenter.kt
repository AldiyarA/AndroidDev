package com.example.todo.presenter

import com.example.todo.contract.TodoDetailInterface
import com.example.todo.model.TodoDetailModel
import com.example.todo.models.Todo

class TodoDetailPresenter(_view: TodoDetailInterface.ViewInterface, todoId: Int) : TodoDetailInterface.PresenterInterface {
    private var view: TodoDetailInterface.ViewInterface = _view
    private var model: TodoDetailModel = TodoDetailModel(todoId)
    private var todo: Todo? = null

    init {
        model.initData(this)
    }

    override fun getUpdatedData(value: Todo) {
        this.todo = value
        view.getDataFromPresenter(value)
    }
}