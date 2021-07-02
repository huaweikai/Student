package com.example.student.StudentRoom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(vararg student: Student)

    @Query("select passwd from student where :user2=user")
//    fun getpasswd(user2:String):LiveData<String>
   suspend fun logrest(user2:String):String

}

