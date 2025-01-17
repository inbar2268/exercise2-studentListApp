package com.example.studentslistapp

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentslistapp.databinding.ActivityStudentDetailsBinding

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnImageButton.setOnClickListener{
            finish()
        }

        var studentId = intent.getStringExtra("student_id")

        val idTextView = findViewById<TextView>(R.id.student_id_text_view);
        val nameTextView = findViewById<TextView>(R.id.student_name_text_view);
        val phoneTextView = findViewById<TextView>(R.id.student_phone_text_view);
        val addressTextView = findViewById<TextView>(R.id.student_address_text_view_);
        val studentCheckBox = findViewById<CheckBox>(R.id.student_checkBox);

        idTextView.text = studentId;

        if (studentId != null) {
            Model.shared.getStudentById(studentId) { student ->
                nameTextView.text = student.name;
                phoneTextView.text = student.phone;
                addressTextView.text = student.address;
                studentCheckBox.isChecked = student.isChecked;
                studentCheckBox.isClickable = false;
            }
        }

    }
}