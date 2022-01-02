package com.example.ticketmastermvvm.data.network

import android.util.Log
import com.example.ticketmastermvvm.core.RetrofitHelper
import com.example.ticketmastermvvm.data.Embedded
import com.example.ticketmastermvvm.data.EventModel
import com.example.ticketmastermvvm.data.Events
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getEvents(): EventModel{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getMxEvents()
            if(response.isSuccessful){
                Log.d("Papu", response.body().toString())
            }
            response.body()!!
        }

    }

}