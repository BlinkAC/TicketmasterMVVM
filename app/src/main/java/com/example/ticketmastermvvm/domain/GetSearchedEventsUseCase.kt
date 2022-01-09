package com.example.ticketmastermvvm.domain

import com.example.ticketmastermvvm.data.EventRepository

class GetSearchedEventsUseCase
    (val keyword: String?,
     val countryCode: String?,
     val segmentName: String?) {
    private val repository = EventRepository()
    suspend operator fun invoke() = repository.getSearchedEvents(keyword, countryCode, segmentName)
}