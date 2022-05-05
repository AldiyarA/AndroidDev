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
import com.example.todo.contract.TodoListInterface
import com.example.todo.databinding.FragmentListBinding
import com.example.todo.models.Todo
import com.example.todo.presenter.TodoListPresenter

class ToDoList() : Fragment(), TodoListInterface.ViewInterface {
    private lateinit var presenter: TodoListPresenter
    private lateinit var binding: FragmentListBinding
    private lateinit var todos: List<Todo>
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
        presenter = TodoListPresenter(this)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun goDetails(todo: Todo){
        val action = ToDoListDirections.actionFromListToDetail(todo.id!!)
        findNavController().navigate(action)
    }

    override fun initView() {
        var recycleView = binding.recycleView

        val llm = LinearLayoutManager(activity)
        val todos = ArrayList<Todo>()
        adapter = ListAdapter(todos){
            goDetails(it)
        }
        Log.e("fragment ", ""+adapter.itemCount)
        recycleView.adapter = adapter
        recycleView.layoutManager = llm
    }

    override fun getDataFromPresenter(value: List<Todo>) {
        Log.e("Response on view", value.toString())
        todos = value
        adapter.setList(todos)
        adapter.notifyItemRangeInserted(0, adapter.itemCount-1)
    }
}