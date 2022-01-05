package com.example.ticketmastermvvm.data.network

import com.example.ticketmastermvvm.data.EventModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {


    @GET("events.json?countryCode=MX&apikey=cZMYDVJB1Z4R8jCaBAdlxlXHUU6GLpB4&source=ticketmaster")
    suspend fun getMxEvents(): Response<EventModel>

    @GET("events.json?apikey=cZMYDVJB1Z4R8jCaBAdlxlXHUU6GLpB4&geoPoint=9u8d4&radius=40")
    suspend fun getNearEvents(): Response<EventModel>
}