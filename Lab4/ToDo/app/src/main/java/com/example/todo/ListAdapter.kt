package com.example.todo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.todo.models.Todo
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private var context: ToDoList, private var dataSet: ArrayList<Todo>, private val clickHandler: (Todo) -> Unit
) :RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val todoName: TextView = view.findViewById(R.id.todoName)
        val status: TextView = view.findViewById(R.id.status)
        val category: TextView = view.findViewById(R.id.category)
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_element , viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val todo = dataSet.get(position)
        viewHolder.todoName.text = todo.title
        if (todo.completed == true){

            viewHolder.status.text = context.getString(R.string.done)
            viewHolder.status.setTextColor(Color.parseColor("#00FF00"))
        }else{
            viewHolder.status.text = context.getString(R.string.not_done)
            viewHolder.status.setTextColor(Color.parseColor("#FF0000"))
        }
        viewHolder.itemView.setOnClickListener {
            clickHandler(dataSet[position])
        }
    }
    fun setList(todos: List<Todo>){
        dataSet = todos as ArrayList<Todo>
    }
    override fun getItemCount() = dataSet.size
}