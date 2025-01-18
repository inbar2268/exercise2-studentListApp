package com.example.studentslistapp.dao


import android.location.Address
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.studentslistapp.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM Student")
    fun getAllStudents(): List<Student>

    @Query("SELECT * FROM Student WHERE id =:id")
    fun getStudentById(id: String): Student

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(vararg students: Student)

    @Query("DELETE FROM Student WHERE id= :id ")
    fun deleteStudent(id: String)

      @Query("UPDATE Student SET name= :name, phone= :phone, address= :address , isChecked= :checked ,id= :id  WHERE id= :oldStudentId ")
  fun updateStudent(oldStudentId:String,name:String, id:String, phone:String, address: String, checked: Boolean)
}
