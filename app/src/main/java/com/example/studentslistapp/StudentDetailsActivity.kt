package com.example.studentslistapp

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentslistapp.MainActivity.Companion.ADD_STUDENT_REQUEST_CODE
import com.example.studentslistapp.databinding.ActivityStudentDetailsBinding

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnImageButton.setOnClickListener {
            finish()
        }
        var studentId = intent.getStringExtra("student_id")

        binding.editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            val currentId = binding.studentIdTextView.text.toString();
            intent.putExtra("student_id", currentId)
            startActivityForResult(intent, EDIT_STUDENT_REQUEST_CODE)

        }

        if (studentId != null) {
            getStudentById(studentId)
        };
    }


    private fun getStudentById(id: String) {
        binding.studentIdTextView.text = id;
        Model.shared.getStudentById(id) { student ->
            binding.studentNameTextView.text = student.name;
            binding.studentPhoneTextView.text = student.phone;
            binding.studentAddressTextView.text = student.address;
            binding.studentCheckBox.isChecked = student.isChecked;
            binding.studentCheckBox.isClickable = false;
        }

    }

    // Handle result when a student is edit
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == EDIT_STUDENT_REQUEST_CODE && resultCode == RESULT_OK) {
            val newStudentId = data?.getStringExtra("edited_student_id")
            newStudentId?.let {
                getStudentById(it)
            }
        }
    }


    companion object {
        const val EDIT_STUDENT_REQUEST_CODE = 2
    }

}