package com.example.mytaskmanager


import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import androidx.room.Query
import androidx.lifecycle.LiveData
import androidx.room.OnConflictStrategy



interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task : Task)


    @Delete
    suspend fun delete(task: Task)


    @Update
    suspend fun update(task: Task)


    @Query("SELECT * FROM tasks_Table order by id ASC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("DELETE FROM tasks_Table")
    suspend fun deleteAllTasks()

}