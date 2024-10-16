package com.dicoding.myevents.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.myevents.R
import com.dicoding.myevents.model.ListEventsItem


class EventAdapter(
    private var events: List<ListEventsItem>,
    private val onItemClick: (ListEventsItem) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: ListEventsItem, onItemClick: (ListEventsItem) -> Unit) {
            // Menggunakan ID yang benar untuk TextView
            itemView.findViewById<TextView>(R.id.eventName).text = event.name
            itemView.findViewById<TextView>(R.id.eventSummary).text = event.description

            Glide.with(itemView.context)
                .load(event.imageLogo) // Assuming imageLogo is a URL
                .into(itemView.findViewById<ImageView>(R.id.eventLogo))

            itemView.setOnClickListener { onItemClick(event) }
        }
    }

    fun updateEvents(newEvents: List<ListEventsItem>) {
        events = newEvents
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position], onItemClick)
    }

    override fun getItemCount(): Int = events.size
}

