package com.droiddaemon.roomdatabaselearning.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class],version = 1)
abstract class StudentDataBase : RoomDatabase() {

    abstract val studentDAO : StudentDao

    companion object{
        @Volatile
        private var INSTANCE : StudentDataBase? = null
        fun getInstance(context: Context):StudentDataBase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDataBase::class.java,
                        "student_data_database"
                    ).build()
                }
                return instance
            }
        }

    }
}