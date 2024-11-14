package com.example.mytaskmanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "tasks_Table")

data class Task (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title")val title :String,
    @ColumnInfo(name = "description")val description :String
)



