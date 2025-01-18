package com.example.studentslistapp

import android.os.Looper
import androidx.core.os.HandlerCompat
import com.example.studentslistapp.dao.AppLocalDb
import com.example.studentslistapp.dao.AppLocalDbRepository
import java.util.concurrent.Executors


typealias StudentsCallback = (List<Student>) -> Unit
typealias StudentCallback = (Student) -> Unit
typealias EmptyCallback = () -> Unit


class Model private constructor() {

    private val database: AppLocalDbRepository = AppLocalDb.database
    private var mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())
    private var executor = Executors.newSingleThreadExecutor()


    companion object {
        val shared = Model()

    }

    fun getAllStudents(callback: StudentsCallback) {
        executor.execute {
            val students = database.studentDao().getAllStudents()
            mainHandler.post {
                callback(students)
            }
        }
    }

    fun getStudentById(studentId: String, callback: StudentCallback) {
        executor.execute {
            val student = database.studentDao().getStudentById(studentId)
            mainHandler.post {
                callback(student)
            }
        }
    }

    fun addStudent(student: Student, callback: EmptyCallback) {
        executor.execute {
            database.studentDao().insertStudent(student)
            mainHandler.post {
                callback()
            }
        }
    }

    fun editStudent(student: Student ,oldStudentId: String, callback: EmptyCallback) {
        executor.execute {
           database.studentDao().updateStudent(oldStudentId,student.name,student.id,student.phone,student.address,student.isChecked)
            mainHandler.post {
                callback()
            }
        }
    }

    fun deleteStudent(studentId: String, callback: EmptyCallback) {
        executor.execute {
            database.studentDao().deleteStudent(studentId)
            mainHandler.post {
                callback()
            }
        }
    }
}