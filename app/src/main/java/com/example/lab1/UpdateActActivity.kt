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

class UpdateActActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_act)

        val activity = ActivitySingleton.activity
        val activityView = ActivitySingleton.activityView

        val titleEditText = findViewById<EditText>(R.id.titleEditText)
        titleEditText.setText(activity?.title)

        val occurenceEditText = findViewById<EditText>(R.id.occDateEditText)
        occurenceEditText.setText(activity?.occurrenceData)

        val jiraLinkEditText = findViewById<EditText>(R.id.jiraLinkEditText)
        jiraLinkEditText.setText(activity?.jiraLink)

        val descriptionYearEditText = findViewById<EditText>(R.id.descriptionEditText)
        descriptionYearEditText.setText(activity?.description)

        val spentHoursEditText = findViewById<EditText>(R.id.spentHoursEditText)
        spentHoursEditText.setText(activity?.spentHours.toString())

        val errorTextView = findViewById<TextView>(R.id.errorTextView)

        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            val updatedTitle = titleEditText.text.toString()
            val updatedOccurenceDate = occurenceEditText.text.toString()
            val updatedJiraLink = jiraLinkEditText.text.toString()
            val updatedDescription = descriptionYearEditText.text.toString()
            val updatedSpentHours = spentHoursEditText.text.toString()

            val validator = InputValidator()

            if(!validator.isInputValid(updatedTitle, updatedOccurenceDate, updatedJiraLink, updatedDescription, updatedSpentHours)) {
                errorTextView.text = "Invalid input. Please check the fields."
                errorTextView.visibility = View.VISIBLE
            }
            else {
                val updatedActivity = activity?.id?.let { it1 ->
                    Activity(
                        it1.toInt(),
                        updatedTitle,
                        updatedOccurenceDate,
                        updatedJiraLink,
                        updatedDescription,
                        updatedSpentHours.toInt()
                    )
                }
                if (updatedActivity != null) {
                    activityView?.updateActivity(updatedActivity)
                }

                finish()
            }
        }

        val cancelButton = findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            finish()
        }
    }
}
