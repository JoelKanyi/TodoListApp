package com.kanyideveloper.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**This is a model class annotated
with @Entity where all the variable will becomes
column name for the table and name of the
model class becomes name of the table.
The name of the class is the table name and the
variables are the columns of the table.*/

//So the table will have three columns- todo_title,todo_priority and the table ID

@Entity(tableName = "todo")

//the class name is the table name
class ToDo(
    @ColumnInfo(name = "todo_title")
    //the variable are the column names
    var title:String = "",
    @ColumnInfo(name = "todo_priority")
    var priority: Int = 0,
    @PrimaryKey(autoGenerate = true) var tId: Int = 0){
    var detail: String = ""
}


