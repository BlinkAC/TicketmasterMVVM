package com.example.ticketmastermvvm.data.categoryModel

import com.example.ticketmastermvvm.data.EventData


//Category title = "near you" || "In your country" || etc
data class AllCategories(var categoryTitle: String, var eventList: List<EventData>)
