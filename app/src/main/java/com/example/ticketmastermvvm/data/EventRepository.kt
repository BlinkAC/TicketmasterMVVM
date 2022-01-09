package com.example.ticketmastermvvm.data

import com.example.ticketmastermvvm.data.eventModel.EventProvider
import com.example.ticketmastermvvm.data.network.EventService

class EventRepository {

    private val api = EventService()

    suspend fun getCountryEvents(): Embedded?{
        val response = api.getCountryEvents().Embedded
        EventProvider.events = response
        return response
    }

    suspend fun getNearEvents(geoPoint: String): Embedded?{
        val response = api.getNearEvents(geoPoint).Embedded
        EventProvider.events = response
        return response
    }

    suspend fun getSearchedEvents(keyword: String?, countryCode: String?, segmentName: String?): Embedded?{
        val response = api.getSearchedEvents(keyword, countryCode, segmentName).Embedded
        EventProvider.events = response
        return  response
    }


}