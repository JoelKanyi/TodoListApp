package com.kanyideveloper.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.add_todo.*
import kotlinx.android.synthetic.main.add_todo.add_todo

class AddTodoActivity: AppCompatActivity(), RadioGroup.OnCheckedChangeListener{

    private var todoDatabase: TodoDatabase? = null
    private var priority = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo)

        //enabling the backpress button on the actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        todoDatabase = TodoDatabase.getInstance(this)
        radioGroup.setOnCheckedChangeListener(this)

        val title = intent.getStringExtra("title")

        //this if condition will work when adding a new todo
        if (title == null || title == ""){

            //adding a todo
            add_todo.setOnClickListener{

                //creating an instance of the ToDo class wich is our table
                //passing the text in the title_ed and priority as arguments
                val todo = ToDo(title_ed.text.toString(), priority)

                //adding a todo details from the detail_ed
                todo.detail = detail_ed.text.toString()

                //saving a todo to the database
                todoDatabase!!.getTodoDao().saveTodo(todo)
                finish()
            }

            //the else part wil function when a user wants to edit an existing todo
        }else{
            add_todo.text = "Update todo"
            val tId = intent.getIntExtra("tId", 0)
            title_ed.setText(title)
            add_todo.setOnClickListener {
                val todo = ToDo(title_ed.text.toString(), priority, tId)
                todo.detail = detail_ed.text.toString()
                todoDatabase!!.getTodoDao().updateTodo(todo)
                finish()
            }
        }
    }


    //Checks a radio button so as to assign each todo a priority

    /**
     * 1-> low priority
     * 2-> medium priority
     * 3-> high priority
     */
    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (checkedId == R.id.medium){
            priority = 2
        }else if (checkedId == R.id.high) {
            priority = 3
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home){
            startActivity(Intent(this, MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}