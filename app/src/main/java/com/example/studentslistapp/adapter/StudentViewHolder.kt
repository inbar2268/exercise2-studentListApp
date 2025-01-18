package com.example.studentslistapp

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.studentslistapp.databinding.ItemStudentBinding

class StudentViewHolder(private val binding: ItemStudentBinding, listener: OnItemClickListener?) :
    RecyclerView.ViewHolder(binding.root) {

    private var student: Student? = null

    init {
        // Initialize the listener
        binding.root.setOnClickListener {
            Log.d("TAG", "On click listener on position $adapterPosition")
            listener?.onItemClick(student)  // Call onItemClick when an item is clicked
        }

        binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            student?.let {
                val newStudent = student?.let {
                    Student(
                        it.id,
                        it.name,
                        it.phone,
                        it.address,
                        isChecked
                    )
                }
                if (newStudent != null) {
                    Model.shared.editStudent(newStudent, it.id) {
                    }
                }
            }

        }
    }

    fun bind(student: Student, position: Int) {
        this.student = student
        binding.studentName.text = student.name
        binding.studentId.text = student.id
        binding.studentImage.setImageResource(R.drawable.logo)
        binding.checkbox.isChecked = student.isChecked

        // on checkbox change
    }
}