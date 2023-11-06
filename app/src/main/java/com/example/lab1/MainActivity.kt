package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.adapter.ActivityAdapter
import com.example.lab1.adapter.ActivityAdapterListener
import com.example.lab1.data.ActivitySingleton
import com.example.lab1.model.Activity
import com.example.lab1.view.ActivityView

class MainActivity : AppCompatActivity(), ActivityAdapterListener {
    private lateinit var activityAdapter: ActivityAdapter
    private lateinit var activityView: ActivityView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityView = ViewModelProvider(this).get(ActivityView::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        activityAdapter = ActivityAdapter(this)
        recyclerView.adapter = activityAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        activityView.getActivitiesLiveData().observe(this) { activities ->
            activityAdapter.submitList(activities)
        }

        val createButton: Button = findViewById(R.id.createButton)
        createButton.setOnClickListener {
            ActivitySingleton.activityView = activityView
            val intent = Intent(this, CreateActActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDeleteActivity(activity: Activity) {
        activityView.deleteActivity(activity.id)
    }

    override fun onDetailsActivity(activity: Activity) {
        ActivitySingleton.activity = activity
        ActivitySingleton.activityView = activityView
        val intent = Intent(this, UpdateActActivity::class.java)
        startActivity(intent)
    }
}
