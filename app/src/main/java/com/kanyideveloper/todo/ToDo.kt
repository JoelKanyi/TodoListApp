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
data class ToDo(
    @PrimaryKey(autoGenerate = true) val tId:Int = 0,
    @ColumnInfo(name = "todo_title") val title:String = "",
    @ColumnInfo(name = "priority") val priority:Int = 0){
    val detail: String = ""
}


