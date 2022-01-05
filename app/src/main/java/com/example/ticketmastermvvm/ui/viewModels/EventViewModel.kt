package com.example.ticketmastermvvm.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketmastermvvm.data.Embedded
import com.example.ticketmastermvvm.domain.GetEventsUseCase
import com.example.ticketmastermvvm.domain.GetNearEventsUseCase
import kotlinx.coroutines.launch

class EventViewModel: ViewModel() {

    val eventModel = MutableLiveData<Embedded?>()
    val nearModel = MutableLiveData<Embedded?>()
    val isLoading = MutableLiveData<Boolean>()
    //val nearModel = MutableLiveData<NearEmbedded?>()
    //val isLoanding
    var getEventsUseCase = GetEventsUseCase()
    var getNearEventsUseCase = GetNearEventsUseCase()

    fun nearEvents(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getNearEventsUseCase()
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