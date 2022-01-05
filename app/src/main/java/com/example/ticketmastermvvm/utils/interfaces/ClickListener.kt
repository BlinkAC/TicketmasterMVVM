package com.example.ticketmastermvvm.utils.interfaces

import android.content.Context
import com.example.ticketmastermvvm.data.EventData

interface ClickListener {
    fun OnItemClick(event: EventData, context: Context)
}