package com.example.mytaskmanager

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room


@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "tasks_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}