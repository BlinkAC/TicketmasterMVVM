package com.example.ticketmastermvvm.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmastermvvm.R
import com.example.ticketmastermvvm.data.EventData
import com.squareup.picasso.Picasso


//This adapter is the one implemented on the pagination branch
//If the scroll is slow makes seem like the progessbar never appears
class PagingAdapter: PagingDataAdapter<EventData, PagingAdapter.ViewHolder>(DataDiff) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {


    }
    object DataDiff: DiffUtil.ItemCallback<EventData>(){

        override fun areItemsTheSame(oldItem: EventData, newItem: EventData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EventData, newItem: EventData): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var image: ImageView = holder.itemView.findViewById(R.id.searchedPoster)
        var title: TextView = holder.itemView.findViewById(R.id.searchedTitle)
        var date: TextView = holder.itemView.findViewById(R.id.searchedDate)
        var locale: TextView = holder.itemView.findViewById(R.id.searchedLocale)

        Picasso.get().load(getItem(position)!!.images[4].url).into(image)
        title.text = getItem(position)!!.name
        date.text = getItem(position)!!.dates!!.start!!.localDate.toString()
        locale.text = getItem(position)!!.locale
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.search_item, parent, false))
    }
}