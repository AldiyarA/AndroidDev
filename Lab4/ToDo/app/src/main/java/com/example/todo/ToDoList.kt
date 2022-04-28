package com.example.todo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.dao.TodoDao
import com.example.todo.databinding.FragmentListBinding
import com.example.todo.models.Category
import com.example.todo.models.ToDo
import com.example.todolist.api.createApiService
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToDoList() : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var todoWithCategoryDao: TodoDao
    private lateinit var todos: List<ToDo>
    private lateinit var adapter: ListAdapter

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
        val service = createApiService()
        Log.e("fragment ", "onViewCreated")

        var recycleView = binding.recycleView

        val llm = LinearLayoutManager(activity)
        val todos = ArrayList<ToDo>()
        adapter = ListAdapter(todos){
            goDetails(it)
        }
        Log.e("fragment ", ""+adapter.itemCount)

        recycleView.adapter = adapter
        recycleView.layoutManager = llm


        service.getTodos().enqueue(object: Callback<List<ToDo>>{
            override fun onResponse(call: Call<List<ToDo>>, response: Response<List<ToDo>>) {
                val todos = response.body() ?: return
                adapter.setList(todos)
                adapter.notifyItemRangeInserted(0, todos.size)
            }

            override fun onFailure(call: Call<List<ToDo>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun goDetails(todo: ToDo){
        val action = ToDoListDirections.actionFromListToDetail(todo.id!!)
        findNavController().navigate(action)
    }
}