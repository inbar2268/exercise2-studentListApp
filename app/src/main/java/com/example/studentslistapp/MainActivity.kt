package com.example.studentslistapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentslistapp.databinding.ActivityMainBinding


interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onItemClick(student: Student?)
}

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var binding: ActivityMainBinding // Initialize binding
    private var students: List<Student> = emptyList() // Initialize with an empty list
    private var adapter: StudentRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Set the root view

        // Set up RecyclerView with LinearLayoutManager
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentRecyclerAdapter(students) // Initialize the adapter with an empty list
        adapter?.listener = this  // Set the listener for handling item clicks
        binding.recyclerView.adapter = adapter

        // Set click listener to add a new student
        binding.btnAddStudent.setOnClickListener {
            val intent = Intent(this, NewStudent::class.java)
            // Start NewStudent activity
            startActivityForResult(intent, ADD_STUDENT_REQUEST_CODE)
        }

        getAllStudents()
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(student: Student?) {
        student?.let {
            TODO("Not yet implemented")
        }
    }

    private fun getAllStudents() {
        Model.shared.getAllStudents { fetchedStudents ->
            students = fetchedStudents // Assign fetched students to the list
            adapter?.set(students) // Update the adapter with the fetched data
            adapter?.notifyDataSetChanged() // Refresh the adapter
        }
    }

    // Handle result when a new student is added
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_STUDENT_REQUEST_CODE && resultCode == RESULT_OK) {
            val newStudent = data?.getSerializableExtra("student_data") as? Student
            newStudent?.let {
                addStudentToDatabase(it)
            }
        }


    }

    // Add student to database and update the list
    private fun addStudentToDatabase(student: Student) {
        Model.shared.addStudent(student) {
            // After adding the student, refresh the list
            getAllStudents()
        }
    }

    companion object {
        const val ADD_STUDENT_REQUEST_CODE = 1
    }

    override fun onResume() {
        super.onResume()
        getAllStudents()
    }

}
