package com.example.ticketmastermvvm.data.network

import com.example.ticketmastermvvm.data.EventModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {


    @GET("events.json?countryCode=MX&apikey=cZMYDVJB1Z4R8jCaBAdlxlXHUU6GLpB4&source=ticketmaster")
    suspend fun getMxEvents(): Response<EventModel>

    @GET("events.json?apikey=cZMYDVJB1Z4R8jCaBAdlxlXHUU6GLpB4&radius=40")
    suspend fun getNearEvents(@Query("geoPoint") geoPoint: String)
    : Response<EventModel>

    @GET("events.json?apikey=cZMYDVJB1Z4R8jCaBAdlxlXHUU6GLpB4")
    suspend fun getSearchedEvents(@Query("keyword") keyword: String?,
                                  @Query("countryCode") countryCode: String?,
                                  @Query("segmentName") segmentName: String?,
                                  @Query("page") page: Int?
                                  )
    : Response<EventModel>
}