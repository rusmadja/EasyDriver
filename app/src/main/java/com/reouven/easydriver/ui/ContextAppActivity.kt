package com.reouven.easydriver.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reouven.easydriver.R


class ContextAppActivity : AppCompatActivity() {

    lateinit var driverId :String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_app)
        val intent = intent
        driverId = intent.getStringExtra("Driverid")


    }
}