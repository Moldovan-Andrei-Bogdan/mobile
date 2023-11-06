package com.example.lab1.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab1.model.Activity

class ActivityView: ViewModel() {
    private val activitiesLiveData = MutableLiveData<List<Activity>>()

    init {
        val initialActivities = listOf<Activity>(
            Activity(1, "Title1", "31.10.2023", "jiraLink1", "description1", 4),
            Activity(2, "Title2", "27.10.2023", "jiraLink2", "description2", 3),
            Activity(3, "Title3", "24.10.2023", "jiraLink3", "description3", 2),
        )
        activitiesLiveData.value = initialActivities
    }

    fun getActivitiesLiveData(): LiveData<List<Activity>> {
        return activitiesLiveData
    }

    fun addActivity(activity: Activity) {
        val currentActivities = activitiesLiveData.value?.toMutableList() ?: mutableListOf()
        currentActivities.add(activity)
        activitiesLiveData.value = currentActivities
    }

    fun updateActivity(activity: Activity) {
        val currentActivities = activitiesLiveData.value?.toMutableList() ?: mutableListOf()
        val index = currentActivities.indexOfFirst{ it.id == activity.id }

        if (index >= 0) {
            currentActivities[index] = activity
        }

        activitiesLiveData.value = currentActivities
    }

    fun deleteActivity(id: Int) {
        val currentActivities = activitiesLiveData.value?.toMutableList() ?: mutableListOf()

        currentActivities.removeAll { it.id == id }

        activitiesLiveData.value = currentActivities
    }
}