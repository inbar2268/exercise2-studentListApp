package com.example.studentslistapp

import android.os.Looper
import androidx.core.os.HandlerCompat
import com.example.studentslistapp.dao.AppLocalDb
import com.example.studentslistapp.dao.AppLocalDbRepository
import java.util.concurrent.Executors


typealias StudentsCallback = (List<Student>)-> Unit

class Model private constructor(){

    private val database: AppLocalDbRepository = AppLocalDb.database
    private var mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())
    private var executor = Executors.newSingleThreadExecutor()


    companion object{
        val shared = Model()

    }

    fun getAllStudents(callback: StudentsCallback) {
        executor.execute{
            val students = database.studentDao().getAllStudents()
            mainHandler.post {
                callback(students)
            }
        }
    }
}