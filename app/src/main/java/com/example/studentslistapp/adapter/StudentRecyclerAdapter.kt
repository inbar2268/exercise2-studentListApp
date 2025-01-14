package com.example.studentslistapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentslistapp.databinding.ActivityMainBinding
import com.example.studentslistapp.databinding.ItemStudentBinding


class StudentRecyclerAdapter(private var students: List<Student>) :

    RecyclerView.Adapter<StudentViewHolder>() {
    var listener: OnItemClickListener? = null

    fun set(students: List<Student>){
        this.students = students
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(
            student = students.get(position),
            position = position
        )
    }

    override fun getItemCount(): Int = students.size ?: 0
}
