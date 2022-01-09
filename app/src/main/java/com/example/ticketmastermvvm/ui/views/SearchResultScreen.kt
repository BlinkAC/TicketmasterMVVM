package com.example.ticketmastermvvm.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmastermvvm.R
import com.example.ticketmastermvvm.data.EventData
import com.example.ticketmastermvvm.data.categoryModel.AllCategories
import com.example.ticketmastermvvm.databinding.ActivityMainBinding
import com.example.ticketmastermvvm.databinding.ActivitySearchResultScreenBinding
import com.example.ticketmastermvvm.ui.viewModels.SearchEventsViewModel
import com.example.ticketmastermvvm.utils.adapter.MainRecyclerViewAdapter
import com.example.ticketmastermvvm.utils.searchAdapter.SearchAdapter

class SearchResultScreen : AppCompatActivity() {
    private val searchViewModel: SearchEventsViewModel by viewModels()
    private var searchRV: RecyclerView? = null
    private lateinit var binding: ActivitySearchResultScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras

        val keyword = bundle?.getString("keyword")
        val countryCode = bundle?.getString("countryCode")
        val segmentName = bundle?.getString("segmentName")


        searchViewModel.getSearchedEvents(keyword, countryCode, segmentName)
        searchViewModel.searchEvents.observe(this, {
            setSearchRV(it!!.events)
        })

        searchViewModel.isLoading.observe(this,{visibility->
            binding.searchLoanding.isVisible = visibility
        })

    }
    private fun setSearchRV(events: List<EventData>){
        searchRV = findViewById(R.id.rvSearch)
        searchRV?.apply {
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@SearchResultScreen)
            searchRV!!.layoutManager = layoutManager
            adapter = SearchAdapter(events)
        }

    }

}

