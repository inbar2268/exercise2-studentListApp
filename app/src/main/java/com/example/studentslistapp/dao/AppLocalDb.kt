package com.example.studentslistapp.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studentslistapp.Student
import com.example.studentslistapp.base.MyApplication


@Database(entities = [Student::class], version = 3)
abstract class AppLocalDbRepository : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}

object AppLocalDb {

    val database: AppLocalDbRepository by lazy {
        val context = MyApplication.Globals.appContext
            ?: throw IllegalStateException("Application context not available")

        Room.databaseBuilder(
            context = context,
            klass = AppLocalDbRepository::class.java,
            name = "dbFileName.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
