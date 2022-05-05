package com.example.todo

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.todo.contract.TodoDetailInterface
import com.example.todo.databinding.FragmentToDoDetailBinding
import com.example.todo.models.Todo
import com.example.todo.presenter.TodoDetailPresenter


class ToDoDetail : Fragment(), TodoDetailInterface.ViewInterface {
    private lateinit var binding: FragmentToDoDetailBinding
    private val args: ToDoDetailArgs by navArgs()
    private lateinit var presenter: TodoDetailPresenter
    private lateinit var todo: Todo


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentToDoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todoId = args.todoId
        presenter = TodoDetailPresenter(this, todoId!!)

    }

    @SuppressLint("SetTextI18n")
    private fun displayTodo(todo: Todo) {
        binding.todoTitle.text = todo.title
//        binding.todoDescription.text = todo.description
        binding.todoCategory.text = "User ${todo.userId}"
//        binding.todoDuration.text = todo.duration
        if (todo.completed == true) {
            binding.todoStatus.text = "Done"
            binding.todoStatus.setTextColor(Color.parseColor("#00FF00"))
        } else {
            binding.todoStatus.text = "Not done"
            binding.todoStatus.setTextColor(Color.parseColor("#FF0000"))
        }
    }

    override fun getDataFromPresenter(value: Todo) {
        todo = value
        displayTodo(todo)
    }
}