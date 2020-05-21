package com.kanyideveloper.todo


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kanyideveloper.todo.R
import com.kanyideveloper.todo.TodoDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TodoAdapter.OnTodoItemClickedListener {

    private var todoDatabase: TodoDatabase? = null
    private var todoAdapter: TodoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoDatabase = TodoDatabase.getInstance(this)
        todoAdapter = TodoAdapter()
        todoAdapter?.setTodoItemClickedListener(this)

        add_todo.setOnClickListener { startActivity(Intent(this, AddTodoActivity::class.java)) }

    }

    //after returning to the main activity
    override fun onResume() {
        super.onResume()
        todoAdapter?.todoList = todoDatabase?.getTodoDao()?.getTodoList()
        todo_rv.adapter = todoAdapter

        //initialising the recyclerview
        todo_rv.layoutManager = LinearLayoutManager(this)
        todo_rv.hasFixedSize()
    }


    //when one item on the recyclerview is clicked
    override fun onTodoItemClicked(todo: ToDo) {
        val intent = Intent(this, AddTodoActivity::class.java)

        //initialising the add todo activity with data from the database
        intent.putExtra("tId", todo.tId)
        intent.putExtra("title", todo.title)
        intent.putExtra("priority", todo.priority)
        intent.putExtra("detail", todo.detail)
        startActivity(intent)
    }

    override fun onTodoItemLongClicked(todo: ToDo) {
        //there is an error with the setItems method,
        //****************to be edited later
        /*val alertDialog = AlertDialog.Builder(this)
            .setItems(R.array.dialog_list, DialogInterface.OnClickListener { dialog, which ->
                if (which==0){
                    val intent = Intent(this, AddTodoActivity::class.java)
                    intent.putExtra("tId", todo.tId)
                    intent.putExtra("title", todo.title)
                    intent.putExtra("priority", todo.priority)
                    intent.putExtra("detail", todo.detail)
                    startActivity(intent)
                }else{
                    todoDatabase?.getTodoDao()?.removeTodo(todo)
                    onResume()
                }
                dialog.dismiss()
            })
            .create()
        alertDialog.show()*/
    }
}