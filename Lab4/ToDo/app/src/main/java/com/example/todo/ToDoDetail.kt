package com.example.todo

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.databinding.FragmentToDoDetailBinding
import com.example.todo.models.ToDo
import com.google.gson.Gson


class ToDoDetail : Fragment() {
    private lateinit var binding: FragmentToDoDetailBinding

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
        val jsonTodo = arguments?.getString("todo", "").toString()
        val todo = Gson().fromJson(jsonTodo, ToDo::class.java)
        displayTodo(todo)
    }

    private fun displayTodo(todo: ToDo) {
        binding.todoTitle.text = todo.title
        binding.todoDescription.text = todo.description
        binding.todoCategory.text = "Home work"
        binding.todoDuration.text = todo.duration
        if (todo.status == true) {
            binding.todoStatus.text = "Done"
            binding.todoStatus.setTextColor(Color.parseColor("#00FF00"))
        } else {
            binding.todoStatus.text = "Not done"
            binding.todoStatus.setTextColor(Color.parseColor("#FF0000"))
        }
    }
}