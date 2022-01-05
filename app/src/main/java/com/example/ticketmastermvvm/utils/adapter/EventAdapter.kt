package com.example.ticketmastermvvm.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmastermvvm.R
import com.example.ticketmastermvvm.data.EventData
import com.example.ticketmastermvvm.utils.interfaces.ClickListener
import com.squareup.picasso.Picasso

class EventAdapter (val eventList: List<EventData>, val listener: ClickListener):
    RecyclerView.Adapter<EventAdapter.EventsViewHolder>(){

    val reversedList = eventList.takeLast(10).reversed()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view: LayoutInflater = LayoutInflater.from(parent.context)
        return EventsViewHolder(view.inflate(R.layout.event_item, parent, false))
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        return holder.bindInfo(reversedList[position])
    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class EventsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.eventImage)

        fun bindInfo(event: EventData){
            itemView.setOnClickListener {
                listener.OnItemClick(event, image.context)
            }
            Picasso.get().load(event.images[4].url).into(image)

        }
    }
}