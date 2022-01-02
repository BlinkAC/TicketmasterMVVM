package com.example.ticketmastermvvm.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmastermvvm.R
import com.example.ticketmastermvvm.adapter.EventAdapter
import com.example.ticketmastermvvm.adapter.MainRecyclerViewAdapter
import com.example.ticketmastermvvm.data.EventData
import com.example.ticketmastermvvm.data.categoryModel.AllCategories
import com.example.ticketmastermvvm.databinding.ActivityMainBinding
import com.example.ticketmastermvvm.ui.viewModels.EventViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val eventViewModel: EventViewModel by viewModels()
    private var mainRecyclerView: RecyclerView? = null
    private var allCategory: MutableList<AllCategories> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventViewModel.onCreate()
        eventViewModel.eventModel.observe(this, Observer {eventList->
            //setRecyclerView(eventList!!.events)
            allCategory.add(AllCategories("Eventos en tu país", eventList!!.events))

            setMainRecyclerView(allCategory)
        })

        eventViewModel.isLoading.observe(this, Observer { visibility->
            binding.loading.isVisible = visibility
        })
    }

    private fun setRecyclerView(events: List<EventData>){
        mainRecyclerView = findViewById(R.id.parentRecyclerView)
        mainRecyclerView?.apply{
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
                this@MainActivity,
            LinearLayoutManager.HORIZONTAL, false)
            mainRecyclerView!!.layoutManager = layoutManager
            adapter = EventAdapter(events)
        }
    }

    private fun setMainRecyclerView(allCategories: List<AllCategories>){
        mainRecyclerView = findViewById(R.id.parentRecyclerView)
        mainRecyclerView?.apply {
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@MainActivity)
            mainRecyclerView!!.layoutManager = layoutManager
            adapter = MainRecyclerViewAdapter(allCategories)
        }

    }
}



