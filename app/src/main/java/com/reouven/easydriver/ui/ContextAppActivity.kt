package com.reouven.easydriver.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.reouven.easydriver.R


class ContextAppActivity : AppCompatActivity() {

    lateinit var driverId :String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_app)
        val intent = intent
        driverId = intent.getStringExtra("Driverid")

        findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav).setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.Profil -> {
                    setTitle("Profil")
                    /*var fragment: Fragment = AppMainFragment()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.app_nav_support,fragment)
                        commit()
                    }*/
                    true
                }
                R.id.NewFeeds -> {
                    setTitle("newFeeds")
                    var fragment: Fragment = AppMainFragment()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.app_nav_support,fragment)
                        commit()
                    }

                    true
                }
                R.id.History -> {
                    setTitle("History")
                    var fragment: Fragment = DriverHistoriyTravelFragment()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.app_nav_support,fragment)
                        commit()
                    }
                    true
                }

                else -> false
            }
        }
    }

}