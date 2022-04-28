package com.example.todo

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.todo.models.ToDo
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private var dataSet: ArrayList<ToDo>, private val clickHandler: (ToDo) -> Unit
) :RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val todoName: TextView = view.findViewById(R.id.todoName)
        val status: TextView = view.findViewById(R.id.status)
        val category: TextView = view.findViewById(R.id.category)
        init {
            view.setOnClickListener {

//                Toast.makeText(context, todoName.text, Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        Log.e("UserListAdapter", "onCreateViewHolder")
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_element , viewGroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val todo = dataSet.get(position)
        viewHolder.todoName.text = todo.title
        if (todo.completed == true){
            viewHolder.status.text = "Done"
            viewHolder.status.setTextColor(Color.parseColor("#00FF00"))
        }else{
            viewHolder.status.text = "Not done"
            viewHolder.status.setTextColor(Color.parseColor("#FF0000"))
        }
        Log.e("", ""+todo.title)
        viewHolder.category.text = "Homework"
        viewHolder.itemView.setOnClickListener {
            clickHandler(dataSet[position])
        }
    }
    fun setList(todos: List<ToDo>){
        dataSet = todos as ArrayList<ToDo>
    }
    override fun getItemCount() = dataSet.size
}