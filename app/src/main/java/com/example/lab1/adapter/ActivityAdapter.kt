package com.example.lab1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.R
import com.example.lab1.model.Activity

interface ActivityAdapterListener {
    fun onDeleteActivity(activity: Activity)
    fun onDetailsActivity(activity: Activity)
}

class ActivityAdapter(private val listener: ActivityAdapterListener) :
    ListAdapter<Activity, ActivityAdapter.ActivityViewHolder>(ActivityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_activity, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = getItem(position)
        holder.bind(activity)
        holder.deleteButton.setOnClickListener {
            listener.onDeleteActivity(activity)
        }
        holder.detailsButton.setOnClickListener {
            listener.onDetailsActivity(activity)
        }
    }

    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val jiraLinkTextView: TextView = itemView.findViewById(R.id.jiraLinkTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
        val detailsButton: Button = itemView.findViewById(R.id.detailsButton)

        fun bind(activity: Activity) {
            titleTextView.text = activity.title
            jiraLinkTextView.text = activity.jiraLink
        }
    }

    private class ActivityDiffCallback : DiffUtil.ItemCallback<Activity>() {
        override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem == newItem
        }
    }
}