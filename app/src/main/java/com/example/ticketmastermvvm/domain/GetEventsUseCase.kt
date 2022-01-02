package com.example.ticketmastermvvm.domain

import com.example.ticketmastermvvm.data.EventRepository

class GetEventsUseCase {

    private val repository = EventRepository()
    suspend operator fun invoke() = repository.getAllEvents()

}