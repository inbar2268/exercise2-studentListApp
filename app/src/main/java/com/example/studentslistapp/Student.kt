package com.example.studentslistapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey var id: String,
    var name: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean
)