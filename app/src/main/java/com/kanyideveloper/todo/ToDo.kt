package com.kanyideveloper.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*This is nothing but a model class annotated
with @Entity where all the variable will becomes
column name for the table and name of the
model class becomes name of the table.
The name of the class is the table name and the
variables are the columns of the table.*/


@Entity(tableName = "todo")
class ToDo(
    @ColumnInfo(name = "todo_title")
    var title:String = "",
    @ColumnInfo(name = "todo_priority")
    var priority: Int = 0,
    @PrimaryKey(autoGenerate = true) var tId: Int = 0){
    var detail: String = ""
}


