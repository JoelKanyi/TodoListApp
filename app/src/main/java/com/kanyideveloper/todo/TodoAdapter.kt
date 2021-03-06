package com.kanyideveloper.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(var todoList: List<ToDo>? = ArrayList<ToDo>()): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    private var onTodoItemClickedListener: OnTodoItemClickedListener?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        //setting the layout depending if there are todos to display
        val layout = if (itemCount == 0) R.layout.empty_view else R.layout.rv_todo_items

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        // !! converts any value to a non-null typeand throws an exception if the value is null
        return TodoViewHolder(view, todoList!!)
    }

    override fun getItemCount(): Int {
        //if the list is empty or has items
        return if(todoList!!.isEmpty()) 0 else todoList!!.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int){
        holder.view.setOnClickListener{onTodoItemClickedListener!!.onTodoItemClicked(todoList!!.get(position))}
        holder.view.setOnLongClickListener{
            onTodoItemClickedListener!!.onTodoItemLongClicked(todoList!!.get(position))
            true
        }
        holder.onBindViews(position)
    }

    inner class TodoViewHolder(val view: View, val todoList: List<ToDo>): RecyclerView.ViewHolder(view){
        fun onBindViews(position: Int){
            if (itemCount != 0){
                view.findViewById<TextView>(R.id.title).text = todoList.get(position).title

                //getting the first letter in the title
                view.findViewById<TextView>(R.id.first_letter).text = todoList.get(position).title.first().toUpperCase().toString()

                //
                view.findViewById<ImageView>(R.id.priority_imgView).setImageResource(getImage(todoList.get(position).priority))
            }

        }
        private fun getImage(priority: Int): Int
                = if (priority == 1) R.drawable.low_priority else if(priority == 2) R.drawable.medium_priority else R.drawable.high_priority
    }

    fun setTodoItemClickedListener(onTodoItemClickedListener: OnTodoItemClickedListener){
        this.onTodoItemClickedListener = onTodoItemClickedListener
    }

    interface OnTodoItemClickedListener{
        fun onTodoItemClicked(todo: ToDo)
        fun onTodoItemLongClicked(todo: ToDo)
    }
}
