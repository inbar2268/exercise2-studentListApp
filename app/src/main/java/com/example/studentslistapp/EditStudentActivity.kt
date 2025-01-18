package com.example.studentslistapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentslistapp.databinding.ActivityEditStudentBinding
import com.example.studentslistapp.databinding.ActivityNewStudentBinding

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelButton.setOnClickListener {
            finish()
        }

        var studentId = intent.getStringExtra("student_id")

        if (studentId != null) {
            Model.shared.getStudentById(studentId) { student ->
                binding.editTextName.setText(student.name)
                binding.editTextId.setText(student.id)
                binding.editTextPhone.setText(student.phone)
                binding.editTextAddress.setText(student.address)
                binding.isCkecked.isChecked = student.isChecked
            }

            binding.deleteButton.setOnClickListener {
                Model.shared.deleteStudent(studentId) {
                    runOnUiThread{
                        val intent = Intent(this,MainActivity::class.java)
                        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }

                }
            }

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
                    Model.shared.editStudent(newStudent, studentId) {
                        val resultIntent = Intent()
                        resultIntent.putExtra("edited_student_id",newStudent.id)
                        setResult(Activity.RESULT_OK, resultIntent)
                        finish()
                    }
                }
            }
        }
    }
}
