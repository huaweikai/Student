package com.example.student.StudentRoom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val user:String,
    val passwd:String
){
    constructor(user: String,passwd: String):this(0,user, passwd)
}