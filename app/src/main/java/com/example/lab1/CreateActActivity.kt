package com.example.lab1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1.data.ActivitySingleton
import com.example.lab1.model.Activity
import com.example.lab1.validator.InputValidator

class CreateActActivity : AppCompatActivity() {
    private var currentIdValue: Int = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_act)

        val cancelButton: Button = findViewById(R.id.cancelButton)
        cancelButton.setOnClickListener {
            finish()
        }

        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
            val title = findViewById<EditText>(R.id.titleEditText).text.toString()
            val occDate = findViewById<EditText>(R.id.occDateEditText).text.toString()
            val jiraLink = findViewById<EditText>(R.id.jiraLinkEditText).text.toString()
            val description = findViewById<EditText>(R.id.descriptionEditText).text.toString()
            val spentHours = findViewById<EditText>(R.id.spentHoursEditText).text.toString()

            val errorTextView = findViewById<TextView>(R.id.errorTextView)

            val validator = InputValidator()

            if(!validator.isInputValid(title, occDate, jiraLink, description, spentHours)) {
                errorTextView.text = "Invalid input. Please check the fields."
                errorTextView.visibility = View.VISIBLE
            }
            else {
                val newActivity = Activity(++currentIdValue, title, occDate, jiraLink, description, spentHours.toInt())

                val activityView = ActivitySingleton.activityView
                activityView?.addActivity(newActivity)

                finish()
            }
        }
    }
}
