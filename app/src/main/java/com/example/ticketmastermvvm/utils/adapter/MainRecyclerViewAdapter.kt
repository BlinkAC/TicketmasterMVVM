package com.example.ticketmastermvvm.utils.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmastermvvm.R
import com.example.ticketmastermvvm.data.EventData
import com.example.ticketmastermvvm.data.categoryModel.AllCategories
import com.example.ticketmastermvvm.ui.views.EventDetails
import com.example.ticketmastermvvm.utils.interfaces.ClickListener

class MainRecyclerViewAdapter(private val allCategory: List<AllCategories>):
    ClickListener,
    RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder>(){

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryTitle: TextView
        var eventRV: RecyclerView

        init {
            categoryTitle = itemView.findViewById(R.id.parent_item_title)
            eventRV = itemView.findViewById((R.id.childRecyclerView))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.parent_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.categoryTitle.text = allCategory[position].categoryTitle
        setChildRecyclerView(holder.eventRV.context, holder.eventRV, allCategory[position].eventList)

    }

    override fun getItemCount(): Int {
        return allCategory.size
    }

    private fun setChildRecyclerView(context: Context,
                                     recyclerView: RecyclerView,
                                     eventsList: List<EventData> ){
        recyclerView.apply {
            val layoutManager: RecyclerView.LayoutManager= LinearLayoutManager(context,RecyclerView.HORIZONTAL,false )
            recyclerView.layoutManager = layoutManager
            adapter = EventAdapter(eventsList, this@MainRecyclerViewAdapter)
        }

    }

    override fun OnItemClick(event: EventData, context: Context) {
        val intent = Intent(context, EventDetails::class.java)
        intent.putExtra("eventId", event.id)
        intent.putExtra("eventName", event.name)
        intent.putExtra("eventPoster", event.images[4].url)
        intent.putExtra("eventLocale", event.locale)
        intent.putExtra("eventPleaseNote", event.pleaseNote)
        intent.putExtra("eventInfo", event.info)
        intent.putExtra("eventDate", event.dates!!.start!!.localDate)
        context.startActivity(intent)
    }
}