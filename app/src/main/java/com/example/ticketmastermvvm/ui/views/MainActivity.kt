package com.example.ticketmastermvvm.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.ticketmastermvvm.R
import com.example.ticketmastermvvm.databinding.ActivityMainBinding
import com.example.ticketmastermvvm.ui.viewModels.EventViewModel

class MainActivity : AppCompatActivity() {

    private val eventViewModel: EventViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        eventViewModel.onCreate()
        eventViewModel.eventModel.observe(this, Observer {
            Log.d("Conciertos", it!!.events[19].name+"\nPrueba: "+it.events[19].pleaseNote)
        })
    }
}



