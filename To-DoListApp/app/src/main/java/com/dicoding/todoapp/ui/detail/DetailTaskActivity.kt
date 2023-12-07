package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val edTittle = findViewById<TextInputEditText>(R.id.detail_ed_title)
        val edDuedate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)
        val edDescription = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val btndelete = findViewById<Button>(R.id.btn_delete_task)

        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[DetailTaskViewModel::class.java]
        viewModel.setTaskId(intent.getIntExtra(TASK_ID, 0))
        viewModel.task.observe(this) { task ->
            if (task != null) {
                edTittle.setText(task.title)
                edDuedate.setText(DateConverter.convertMillisToString(task.dueDateMillis))
                edDescription.setText(task.description)
            }
        }

        btndelete.setOnClickListener {
            viewModel.deleteTask()
            Toast.makeText(this, "Data terhapus", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

    }
}