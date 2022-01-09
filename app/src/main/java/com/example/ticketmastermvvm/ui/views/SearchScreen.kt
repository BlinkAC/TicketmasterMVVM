package com.example.ticketmastermvvm.ui.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.example.ticketmastermvvm.R
import com.example.ticketmastermvvm.databinding.ActivitySearchScreenBinding

class SearchScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySearchScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextQuery.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {

                //Perform Code
                val queryTitle: String = binding.editTextQuery.text.toString()
                val intent = Intent(this, SearchResultScreen::class.java)
                intent.putExtra("keyword", queryTitle)
                startActivity(intent)
                binding.editTextQuery.text.clear()
                hideKeyboard()

                return@OnKeyListener true
            }
            false
        })

        binding.buttonEvents.setOnClickListener {
            val intent = Intent(this, SearchResultScreen::class.java)
            intent.putExtra("countryCode", "MX")
            startActivity(intent)
        }

        binding.buttonConcerts.setOnClickListener {
            val intent = Intent(this, SearchResultScreen::class.java)
            intent.putExtra("countryCode", "MX")
            intent.putExtra("segmentName", "Music")
            startActivity(intent)
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(view.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}