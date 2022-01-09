package com.example.ticketmastermvvm.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketmastermvvm.data.Embedded
import com.example.ticketmastermvvm.domain.GetSearchedEventsUseCase
import kotlinx.coroutines.launch

class SearchEventsViewModel: ViewModel() {

    val searchEvents = MutableLiveData<Embedded?>()
    val isLoading = MutableLiveData<Boolean>()

    fun getSearchedEvents(keyword: String?, countryCode: String?, segmentName: String?){
        viewModelScope.launch {
            isLoading.postValue(true)
            var result = GetSearchedEventsUseCase(keyword, countryCode, segmentName).invoke()
            searchEvents.postValue(result)
            isLoading.postValue(false)
        }

    }
}