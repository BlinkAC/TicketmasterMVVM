package com.example.ticketmastermvvm.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmastermvvm.R
import com.example.ticketmastermvvm.core.RetrofitHelper
import com.example.ticketmastermvvm.databinding.ActivitySearchResultScreenBinding

import com.example.ticketmastermvvm.ui.viewModels.SearchEventsViewModel
import com.example.ticketmastermvvm.ui.viewModels.SearchViewModel
import com.example.ticketmastermvvm.ui.viewModels.SearchViewModelFactory
import com.example.ticketmastermvvm.utils.adapter.EventsLoadStateAdapter
import com.example.ticketmastermvvm.utils.adapter.PagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SearchResultScreen : AppCompatActivity() {
    //private val searchViewModel: SearchEventsViewModel by viewModels()

    private lateinit var viewModel: SearchViewModel

    private var searchRV: RecyclerView? = null
    private lateinit var binding: ActivitySearchResultScreenBinding
    private var searchEventAdapter: PagingAdapter = PagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras

        val keyword = bundle?.getString("keyword")
        val countryCode = bundle?.getString("countryCode")
        val segmentName = bundle?.getString("segmentName")


        viewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(RetrofitHelper, keyword, countryCode, segmentName)
        )[SearchViewModel::class.java]

        lifecycleScope.launch {
            viewModel.eventList.collectLatest {
                searchEventAdapter.submitData(it)
            }
        }


        bindUi()



    }
   private fun bindUi() {
       searchRV = binding.rvSearch


        searchRV.apply {
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@SearchResultScreen)
            searchRV!!.layoutManager = layoutManager
            searchRV!!.adapter = searchEventAdapter.withLoadStateHeaderAndFooter(
                header = EventsLoadStateAdapter{ searchEventAdapter.retry() },
                footer = EventsLoadStateAdapter{ searchEventAdapter.retry() }
            )

        }
   }




    private fun showEmptyList(isEmptyList: Boolean) {
        binding.rvSearch.isVisible = isEmptyList
        if(isEmptyList){
            Log.d("gatita", "sin resultados")
        }
    }
}

