package com.example.ticketmastermvvm.data.network

import com.example.ticketmastermvvm.data.Embedded
import com.example.ticketmastermvvm.data.EventModel
import com.example.ticketmastermvvm.data.EventData
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {


    @GET("events.json?countryCode=MX&apikey=cZMYDVJB1Z4R8jCaBAdlxlXHUU6GLpB4&source=ticketmaster")
    suspend fun getMxEvents(): Response<EventModel>
}