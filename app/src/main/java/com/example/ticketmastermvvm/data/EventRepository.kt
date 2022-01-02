package com.example.ticketmastermvvm.data

import com.example.ticketmastermvvm.data.eventModel.EventProvider
import com.example.ticketmastermvvm.data.network.EventService

class EventRepository {

    private val api = EventService()

    suspend fun getAllEvents(): Embedded?{
        val response = api.getEvents().Embedded
        EventProvider.events = response
        return response
    }


}