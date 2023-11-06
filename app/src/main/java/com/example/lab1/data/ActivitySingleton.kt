package com.example.lab1.data

import com.example.lab1.model.Activity
import com.example.lab1.view.ActivityView

object ActivitySingleton {
    var activityView: ActivityView? = null
    var activity: Activity? = null
}