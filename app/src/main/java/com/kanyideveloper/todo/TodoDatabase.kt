package com.kanyideveloper.todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
This is an abstract class where you define all the entities
that means all the tables that you want to create for that database.
We define the list of operation that we would like to perform on table.
**/


/**
 * Note: I have called
 * allowMainThreadQueries method when creating the database,
 * this is not a good practice, operations such as querying a
 * database are not supposed to be done on the main thread, **/

@Database(entities = [ToDo::class],version = 1, exportSchema = false)

abstract class TodoDatabase : RoomDatabase(){

    //an abstract method that return a DAO for the database
    abstract fun getTodoDao():TodoDao

     //A singleton design pattern is used to ensure that the database instance created is one
    companion object{
        val databaseName = "tododatabase"

         //creating an instance of the Database class
        var todoDatabase: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase?{
            if(todoDatabase == null){
                todoDatabase = Room.databaseBuilder(context,
                TodoDatabase::class.java,
                TodoDatabase.databaseName)
                    .allowMainThreadQueries() ////i will remove this later, database are not supposed to be called on main thread
                    .build()
            }
            return todoDatabase
        }
    }
}