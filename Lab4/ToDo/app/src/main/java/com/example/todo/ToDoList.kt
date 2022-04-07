package com.example.todo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.dao.TodoDao
import com.example.todo.databinding.FragmentListBinding
import com.example.todo.models.Category
import com.example.todo.models.ToDo
import com.google.gson.Gson

class ToDoList(private val todoDao: TodoDao) : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var todoWithCategoryDao: TodoDao
    private lateinit var todos: List<ToDo>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("fragment ", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)

        Log.e("fragment ", "onCreateView")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todos = todoDao.getAll()

        Log.e("fragment ", "onViewCreated")

        var recycleView = binding.recycleView

        val llm = LinearLayoutManager(activity)
        val la = ListAdapter(todos as ArrayList<ToDo>){
            goDetails(it)
        }
        Log.e("fragment ", ""+la.itemCount)

        recycleView.adapter = la
        recycleView.layoutManager = llm

    }

    private fun genElements(): ArrayList<ToDo> {
        val category = Category(1, "Home work")

        val todos = ArrayList<ToDo>();

        for (i in 10..15) {
            val todo = ToDo(i, "Task $i", "Do android lab $i", false, 1, "today")
            todoDao.insert(todo)
            todos.add(todo)
        }
        Log.e("", ""+todos)
        return todos
    }

    private fun goDetails(todo: ToDo){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        var bundle = Bundle()
        bundle.putString("todo", Gson().toJson(todo))
        var todoDetailFragment = ToDoDetail()
        todoDetailFragment.arguments = bundle
        transaction?.replace(R.id.fragment, todoDetailFragment)
        transaction?.addToBackStack("todo-details")
        transaction?.commit()
    }
}