package com.example.ticketmastermvvm.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketmastermvvm.data.Embedded
import com.example.ticketmastermvvm.data.EventRepository
import com.example.ticketmastermvvm.data.db.Event
import com.example.ticketmastermvvm.domain.GetEventsUseCase
import com.example.ticketmastermvvm.domain.GetNearEventsUseCase
import kotlinx.coroutines.launch

class EventViewModel(): ViewModel() {



    val eventModel = MutableLiveData<Embedded?>()
    val nearModel = MutableLiveData<Embedded?>()
    val isLoading = MutableLiveData<Boolean>()
    //val nearModel = MutableLiveData<NearEmbedded?>()
    //val isLoanding
    //var geoPoint:String = "dr5re"
    var getEventsUseCase = GetEventsUseCase()
    //var getNearEventsUseCase =

    fun nearEvents(geoPoint: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = GetNearEventsUseCase(geoPoint).invoke()
            nearModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun onCreate(){
        viewModelScope.launch {
            val result = getEventsUseCase()
            eventModel.postValue(result)

        }
    }



}