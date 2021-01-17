package com.reouven.easydriver.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.reouven.easydriver.R
import com.reouven.easydriver.ui.fragment.appFragment.ProfilFragment
import com.reouven.easydriver.ui.fragment.navFragmentSupport.AppSupportFragment
import com.reouven.easydriver.ui.fragment.navFragmentSupport.AppSupportFragmentNewFeed


class ContextAppActivity : AppCompatActivity() {

    lateinit var driverId: String
    lateinit var menu: com.google.android.material.bottomnavigation.BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_app)


        val intent = intent
        driverId = intent.getStringExtra("Driverid")


        menu = findViewById(R.id.bottomNav)
        menu.selectedItemId = R.id.NewFeeds
        menu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.Profil -> {
                    setTitle("Profil")
                    var fragment: Fragment = ProfilFragment()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.app_nav_support, fragment)
                        commit()
                    }
                    true
                }
                R.id.NewFeeds -> {
                    setTitle("newFeeds")
                    var fragment: Fragment = AppSupportFragmentNewFeed()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.app_nav_support, fragment)
                        commit()
                    }

                    true
                }
                R.id.History -> {
                    setTitle("History")
                    var fragment: Fragment = AppSupportFragment()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.app_nav_support, fragment)
                        commit()
                    }
                    true
                }

                else -> false
            }
        }
    }

}