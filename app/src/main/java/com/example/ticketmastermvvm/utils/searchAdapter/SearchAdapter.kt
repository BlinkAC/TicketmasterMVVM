package com.example.ticketmastermvvm.utils.searchAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmastermvvm.R
import com.example.ticketmastermvvm.data.EventData
import com.squareup.picasso.Picasso


class SearchAdapter(val eventList: List<EventData>):

    RecyclerView.Adapter<SearchAdapter.SearchEventsViewHolder>() {

    inner class SearchEventsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.searchedPoster)
        var title: TextView = itemView.findViewById(R.id.searchedTitle)
        var date: TextView = itemView.findViewById(R.id.searchedDate)
        var locale: TextView = itemView.findViewById(R.id.searchedLocale)

        fun bindInfo(event: EventData){
            Picasso.get().load(event.images[5].url).into(image)
            title.text = event.name
            date.text = event.dates!!.start!!.localDate
            locale.text = event.locale
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchEventsViewHolder {
       val view: LayoutInflater = LayoutInflater.from(parent.context)
        return SearchEventsViewHolder(view.inflate(R.layout.search_item, parent, false))
    }

    override fun onBindViewHolder(holder: SearchEventsViewHolder, position: Int) {
        return holder.bindInfo(eventList[position])
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}