package com.reouven.easydriver.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reouven.easydriver.R

/**
 * this Activity contain all the connections fragments of the Application
 * the LoginFragment , the Signin and the PhoneVerifFragment
 * */
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}