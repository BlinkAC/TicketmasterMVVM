package com.example.ticketmastermvvm.ui.views

import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketmastermvvm.R
import com.example.ticketmastermvvm.data.EventData
import com.example.ticketmastermvvm.data.categoryModel.AllCategories
import com.example.ticketmastermvvm.databinding.ActivityMainBinding
import com.example.ticketmastermvvm.ui.viewModels.EventViewModel
import ch.hsr.geohash.GeoHash
import com.example.ticketmastermvvm.utils.adapter.EventAdapter
import com.example.ticketmastermvvm.utils.adapter.MainRecyclerViewAdapter
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    private val eventViewModel: EventViewModel by viewModels()
    private var mainRecyclerView: RecyclerView? = null
    private var allCategory: MutableList<AllCategories> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myToolbar: Toolbar = findViewById(R.id.myToolbar)
        val drawerLayout: DrawerLayout = binding.drawerMenu
        val navView: NavigationView = binding.myNavView
        toggle = ActionBarDrawerToggle(this, drawerLayout, myToolbar, R.string.open, R.string.close)
        toggle.syncState()
        setSupportActionBar(myToolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> Toast.makeText(this, "Se presiono home", Toast.LENGTH_LONG).show()
                R.id.nav_favs -> Toast.makeText(this, "Se preciono favs", Toast.LENGTH_LONG).show()
            }
            true
        }

        val bundle: Bundle? = intent.extras
        val lat = bundle?.getString("latitude").toString()
        val long = bundle?.getString("longitude").toString()



        eventViewModel.onCreate()
        eventViewModel.eventModel.observe(this, Observer {eventList->
            allCategory.add(AllCategories("Eventos en tu país", eventList!!.events))

        })

        eventViewModel.nearEvents(getGeoHash(lat, long))
        eventViewModel.nearModel.observe(this, {nearEventList ->
            allCategory.add(AllCategories("Eventos cerca de ti", nearEventList!!.events))
            setMainRecyclerView(allCategory)

        })

        eventViewModel.isLoading.observe(this, Observer { visibility->
            binding.loading.isVisible = visibility
        })


        binding.myToolbar.searchPageButton.setOnClickListener {
            val intent = Intent(this,SearchScreen::class.java)
            startActivity(intent)
        }


    }

    //9u8d4
    private fun getGeoHash(lat: String, long: String): String{
        val location = Location("geohash")
        location.latitude = lat.toDouble()
        location.longitude = long.toDouble()
        val hash = GeoHash.withCharacterPrecision(location.latitude, location.longitude, 5)
        return hash.toBase32()
    }


    private fun setMainRecyclerView(allCategories: List<AllCategories>){
        mainRecyclerView = findViewById(R.id.parentRecyclerView)
        mainRecyclerView?.apply {
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@MainActivity)
            mainRecyclerView!!.layoutManager = layoutManager
            adapter = MainRecyclerViewAdapter(allCategories)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

