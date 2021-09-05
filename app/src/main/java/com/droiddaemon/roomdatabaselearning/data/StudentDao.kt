package com.droiddaemon.roomdatabaselearning.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {


    @Insert
    suspend fun insertStudent(subscriber: Student) : Long

    @Update
    suspend fun updateStudent(subscriber: Student) : Int

    @Delete
    suspend fun deleteStudent(subscriber: Student) : Int

    @Query("DELETE FROM student_data_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM student_data_table")
    fun getAllStudent(): LiveData<List<Student>>

}