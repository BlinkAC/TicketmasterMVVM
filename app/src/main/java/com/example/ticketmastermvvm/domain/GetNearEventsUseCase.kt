package com.example.ticketmastermvvm.domain

import com.example.ticketmastermvvm.data.EventRepository

class GetNearEventsUseCase(val geoPoint: String) {
    private val repository = EventRepository()
    suspend operator fun invoke() = repository.getNearEvents(geoPoint)
}