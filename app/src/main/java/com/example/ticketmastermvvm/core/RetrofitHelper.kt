package com.example.ticketmastermvvm.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    //https://app.ticketmaster.com/discovery/v2/attractions.json?keyword=Kenia&apikey=cZMYDVJB1Z4R8jCaBAdlxlXHUU6GLpB4
    //https://app.ticketmaster.com/discovery/v2/events.json?countryCode=MX&apikey=cZMYDVJB1Z4R8jCaBAdlxlXHUU6GLpB4&source=ticketmaster
    //geoPoint Eventos cerca de ti con lat-long y geoHash
    //nomas ticketmaster &source=ticketmaster
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://app.ticketmaster.com/discovery/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}