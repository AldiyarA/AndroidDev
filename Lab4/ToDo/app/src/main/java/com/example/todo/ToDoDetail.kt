package com.example.todo

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.todo.dao.TodoDao
import com.example.todo.databinding.FragmentToDoDetailBinding
import com.example.todo.models.ToDo
import com.example.todolist.api.createApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ToDoDetail : Fragment() {
    private lateinit var binding: FragmentToDoDetailBinding
    private lateinit var db: AppDatabase
    private lateinit var todoDao: TodoDao
    private val args: ToDoDetailArgs by navArgs()

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
        val service = createApiService()

//
//        val jsonTodo = arguments?.getString("todo", "").toString()
//        val todo = Gson().fromJson(jsonTodo, ToDo::class.java)
        val todoId = args.todoId
        service.getTodoById(todoId).enqueue(object: Callback<ToDo>{
            override fun onResponse(call: Call<ToDo>, response: Response<ToDo>) {
                val todo = response.body() ?: return
                displayTodo(todo)
            }

            override fun onFailure(call: Call<ToDo>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun displayTodo(todo: ToDo) {
        binding.todoTitle.text = todo.title
        binding.todoDescription.text = todo.description
        binding.todoCategory.text = "Home work"
        binding.todoDuration.text = todo.duration
        if (todo.completed == true) {
            binding.todoStatus.text = "Done"
            binding.todoStatus.setTextColor(Color.parseColor("#00FF00"))
        } else {
            binding.todoStatus.text = "Not done"
            binding.todoStatus.setTextColor(Color.parseColor("#FF0000"))
        }
    }
}