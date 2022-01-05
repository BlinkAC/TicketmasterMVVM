package com.example.ticketmastermvvm.data.network

import android.util.Log
import com.example.ticketmastermvvm.core.RetrofitHelper
import com.example.ticketmastermvvm.data.Embedded
import com.example.ticketmastermvvm.data.EventModel
import com.example.ticketmastermvvm.data.EventData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCountryEvents(): EventModel{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getMxEvents()
            if(response.isSuccessful){
                Log.d("Papu", response.body().toString())
            }
            response.body()!!
        }

    }

    suspend fun getNearEvents(): EventModel{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getNearEvents()
            if(response.isSuccessful){
                Log.d("Papu", response.body().toString())
            }
            response.body()!!
        }

    }

}