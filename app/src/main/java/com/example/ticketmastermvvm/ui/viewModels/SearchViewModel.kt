package com.example.ticketmastermvvm.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.ticketmastermvvm.core.RetrofitHelper
import com.example.ticketmastermvvm.data.EventPagingSource
//If the scroll is slow makes seem like the progessbar never appears
class SearchViewModel(private val service: RetrofitHelper, private val keyword: String?,
                      private val countryCode: String?, private val segmentName: String?): ViewModel() {



    val eventList = Pager(PagingConfig(initialLoadSize = 20, pageSize =10)){
        EventPagingSource(service, keyword, countryCode, segmentName)
    }.flow.cachedIn(viewModelScope)
}