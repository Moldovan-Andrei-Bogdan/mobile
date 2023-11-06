package com.example.lab1.model

data class Activity (
    val id: Int,
    val title: String,
    val occurrenceData: String,
    val jiraLink: String,
    val description: String,
    val spentHours: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Activity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}