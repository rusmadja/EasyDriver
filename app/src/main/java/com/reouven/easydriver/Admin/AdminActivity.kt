package com.reouven.easydriver.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reouven.easydriver.R

/**
 * this Activity contain all the Admin fragments of the Application
 * after be registered as an admin you have access to a list of all the Travel with the status "CLOSE"
 * */

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
    }
}