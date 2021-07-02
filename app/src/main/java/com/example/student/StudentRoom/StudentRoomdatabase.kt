package com.example.student.StudentRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class],version = 1,exportSchema = false)
abstract class StudentRoomdatabase: RoomDatabase() {
    abstract fun getStudentDao():StudentDao
    companion object{
        @Volatile private var INSTANCE:StudentRoomdatabase?=null

        fun getDatabase(context: Context):StudentRoomdatabase?{
            INSTANCE?: synchronized(this){
                INSTANCE= Room.databaseBuilder(context.applicationContext,
                StudentRoomdatabase::class.java,"Student")
                    .build()
            }
            return INSTANCE
        }
    }
}