package com.kanyideveloper.todo

import androidx.room.*
import com.kanyideveloper.todo.ToDo
/**
This is an interface which acts is an
intermediary between the user and the database.
All the operation to be performed on a table
 has to be defined here.
 We define the list of operation that we would
 like to perform on table.
*/

@Dao
interface TodoDao{
    /**
     * SELECT -> This retrieve rows from a table in a database
     * FROM -> You specify the table to retrieve the rows from
     * ORDER BY -> This is just a sort algorithm
     * ASC -> Ascending order
     * WHERE -> This is a condition used to query data
     * */
    @Query("SELECT*FROM todo ORDER BY tId ASC")
    //function to list all todo
    fun getTodoList(): List<ToDo>


    @Query("SELECT*FROM todo WHERE tId=:tid")
    //function to get a specific todo
    fun getTodoItem(tid: Int): ToDo
    /**
     * @param todo is what we want to save in our database
     * so many conflict can occur when a data is to be saved, the strategy is used to handle such conflicts
     * Abort -> this aborts the transaction
     * Ignore -> this ignores and continues the transaction
     * Replace -> this replace the data
     * others includes fail, and roolback
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //add a new todo to the database
    fun saveTodo(todo: ToDo)

    @Update
    //making changes to an existing todo
    fun updateTodo(todo: ToDo)

    @Delete
    //deletes a todo from the database
    fun removeTodo(todo: ToDo)
}