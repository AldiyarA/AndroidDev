package com.example.todo.models

class Todo (
    var id: Int? = null,
    var userId: Int? = null,
    var title: String? = null,
    var completed: Boolean? = null,
) {
    companion object {
        const val serializedName: String = "todo"
    }
}