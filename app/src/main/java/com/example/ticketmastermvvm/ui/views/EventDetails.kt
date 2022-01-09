package com.example.ticketmastermvvm.ui.views

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.ticketmastermvvm.R
import com.example.ticketmastermvvm.databinding.ActivityEventDetailsBinding
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.squareup.picasso.Picasso

class EventDetails : AppCompatActivity() {
    private lateinit var binding: ActivityEventDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myEventPoster: ImageView = binding.posterEvent
        val myEventName: CollapsingToolbarLayout = binding.collapsingToolbar
        val myEventInfo: TextView = binding.eventInfo
        val myPleaseNote: TextView = binding.eventPLeaseNote

        myEventName.setExpandedTitleColor(Color.WHITE)
        myEventName.setCollapsedTitleTextColor(Color.WHITE)

        val bundle: Bundle? = intent.extras
        val eventID = bundle?.getString("eventId")
        val eventName = bundle?.getString("eventName")
        val eventInfo = bundle?.getString("eventInfo")
        val eventPoster = bundle?.getString("eventPoster")
        val eventLocale = bundle?.getString("eventLocale")
        val pleaseNote = bundle?.getString("eventPleaseNote")


        Picasso.get().load(eventPoster).into(myEventPoster)
        myEventName.title = eventName
        if(eventInfo!=null && pleaseNote!=null){
            myEventInfo.text = splitInfo(eventInfo)
            myPleaseNote.text = splitNotes(pleaseNote)
        }else{
            myEventInfo.text = "No hay información por mostrar"
        }
    }

    //Json response comes without \s or \n, spliting event info and please notes
    private fun splitInfo(eventInfo: String): StringBuilder{
        var splitInfo: List<String> = emptyList()

        if(eventInfo!=null){
            //([a-z])+[.]+[\s]
            splitInfo = eventInfo.split(Regex("[.]+[\\s]"))
        }

        val builder: StringBuilder = StringBuilder()
        for(element in splitInfo){
            builder.append("$element.")
            builder.append("\n")
            builder.append("\n")

        }
        return builder
    }

    //|[A-Z][íóáa-z\s]+:
    //https://images.pexels.com/photos/2147029/pexels-photo-2147029.jpeg
    private fun splitNotes(eventNotes: String):StringBuilder{
        var splitNotes: List<String> = emptyList()

        if(eventNotes!=null){
            splitNotes = eventNotes.split(Regex("[A-Z][a-z\\s]+:|[A-Z][íóáa-z\\s]+:"))
        }

        val builder: StringBuilder = StringBuilder()
        for(element in splitNotes){
            builder.append(element)
            builder.append("\n")
        }
        return builder

    }
}