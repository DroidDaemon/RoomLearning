package com.droiddaemon.roomdatabaselearning.data

class StudentRepository (private val dao : StudentDao) {

    val subscribers = dao.getAllStudent()

    suspend fun insert(student: Student):Long{
        return dao.insertStudent(student)
    }

    suspend fun update(student: Student):Int{
        return dao.updateStudent(student)
    }

    suspend fun delete(student: Student) : Int{
        return dao.deleteStudent(student)
    }

    suspend fun deleteAll() : Int{
        return dao.deleteAll()
    }
}