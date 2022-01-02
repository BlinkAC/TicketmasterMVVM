package com.example.ticketmastermvvm.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketmastermvvm.data.Embedded
import com.example.ticketmastermvvm.data.EventModel
import com.example.ticketmastermvvm.data.Events
import com.example.ticketmastermvvm.domain.GetEventsUseCase
import kotlinx.coroutines.launch

class EventViewModel: ViewModel() {

    val eventModel = MutableLiveData<Embedded?>()
    //val isLoanding
    var getEventsUseCase = GetEventsUseCase()

    fun onCreate(){
        viewModelScope.launch {
            val result = getEventsUseCase()

            eventModel.postValue(result)
        }
    }
}