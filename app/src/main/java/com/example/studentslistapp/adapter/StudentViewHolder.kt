package com.example.studentslistapp

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.studentslistapp.databinding.ItemStudentBinding

class StudentViewHolder(private val binding: ItemStudentBinding, listener: OnItemClickListener?) : RecyclerView.ViewHolder(binding.root) {

    private var student: Student? = null

    init {
        // Initialize the listener
        binding.root.setOnClickListener {
            Log.d("TAG", "On click listener on position $adapterPosition")
            listener?.onItemClick(student)  // Call onItemClick when an item is clicked
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