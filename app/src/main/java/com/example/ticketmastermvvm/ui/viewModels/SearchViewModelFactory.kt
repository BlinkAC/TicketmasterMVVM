package com.example.ticketmastermvvm.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ticketmastermvvm.core.RetrofitHelper

class SearchViewModelFactory(
    private val service: RetrofitHelper, private val keyword: String?,
    private val countryCode: String?, private val segmentName: String?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(service, keyword,
                                    countryCode, segmentName) as T
        }
        throw IllegalArgumentException("not found view model")
    }
}