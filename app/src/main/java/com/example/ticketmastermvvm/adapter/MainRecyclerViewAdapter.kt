package com.example.ticketmastermvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmastermvvm.R
import com.example.ticketmastermvvm.data.EventData
import com.example.ticketmastermvvm.data.categoryModel.AllCategories

class MainRecyclerViewAdapter(private val allCategory: List<AllCategories>):
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
            adapter = EventAdapter(eventsList)
        }

    }
}