package com.example.studentslistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentslistapp.databinding.ActivityNewStudentBinding

class NewStudent: AppCompatActivity() {

    private lateinit var binding: ActivityNewStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelButton.setOnClickListener{
            finish()
        }


        // Handle Save button click
        binding.saveButton.setOnClickListener {
            val id = binding.editTextId.text.toString().trim()
            val name = binding.editTextName.text.toString().trim()
            val phone = binding.editTextPhone.text.toString().trim()
            val address = binding.editTextAddress.text.toString().trim()
            val isChecked = binding.isCkecked.isChecked

            if (id.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {

                val newStudent = Student(id, name, phone, address, isChecked)
                Model.shared.addStudent(newStudent){
                    val resultIntent = Intent()
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }
}

