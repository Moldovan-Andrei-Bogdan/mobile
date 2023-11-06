package com.example.lab1.validator

import androidx.core.text.isDigitsOnly

class InputValidator {
    fun isInputValid(
        title: String,
        occDate: String,
        jiraLink: String,
        description: String,
        spentHours: String
    ): Boolean {
        if (title.isBlank() || occDate.isBlank() || jiraLink.isBlank() || description.isBlank() || spentHours.isBlank()) {
            return false
        }

        try {
            val spentHoursInt = spentHours.toInt()
            return true
        }
        catch (e: NumberFormatException) {
            return false
        }
    }
}