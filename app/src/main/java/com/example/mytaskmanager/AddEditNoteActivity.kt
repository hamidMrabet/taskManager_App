package com.example.mytaskmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.Date

class AddEditNoteActivity : AppCompatActivity() {
    private lateinit var TitleEdt: EditText
    private lateinit var discrpEdt: EditText
    private lateinit var savebtn: Button


    lateinit var viewMod: TaskViewModel
    var taskID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_task)

        // on below line we are initializing our view modal.
        viewMod = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TaskViewModel::class.java)

        // on below line we are initializing all our variables.
        TitleEdt = findViewById(R.id.idEdit_Title)
        discrpEdt = findViewById(R.id.idEdit_Desc)
        savebtn = findViewById(R.id.save_Btn)

        // on below line we are getting data passed via an intent.
        val noteType = intent.getStringExtra("taskType")
        if (noteType.equals("Edit")) {
            // on below line we are setting data to edit text.
            val taskTiTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            taskID = intent.getIntExtra("taskId", -1)
            savebtn.setText("Atualizar Tarefa")
            TitleEdt.setText(noteTitle)
            discrpEdt.setText(noteDescription)
        } else {
            savebtn.setText("Salvar Tarefa")
        }

        // on below line we are adding
        // click listener to our save button.
        savebtn.setOnClickListener {
            // on below line we are getting
            // title and desc from edit text.
            val noteTitle = TitleEdt.text.toString()
            val noteDescription = discrpEdt.text.toString()
            // on below line we are checking the type
            // and then saving or updating the data.
            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedTask = Task(noteTitle, noteDescription, currentDateAndTime)
                    updatedTask.id = noteID
                    viewMod.updateTask(updatedTask)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    // if the string is not empty we are calling a
                    // add note method to add data to our room database.
                    viewMod.addTask(Task(noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            // opening the new activity on below line
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}