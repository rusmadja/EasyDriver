package com.reouven.easydriver.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.reouven.easydriver.R
import com.reouven.easydriver.ui.fragment.appFragment.ProfilFragment
import com.reouven.easydriver.ui.fragment.navFragmentSupport.AppSupportFragment
import com.reouven.easydriver.ui.fragment.navFragmentSupport.AppSupportFragmentNewFeed

/**
 * this Activity contain all the List fragments of the Application
 * the DriverHistoryFragment , the AppMainFragment ( the list of all the travel with the Status "SEND")
 * */
class ContextAppActivity : AppCompatActivity() {

    /** variables declarations  */
    lateinit var driverId: String
    lateinit var menu: com.google.android.material.bottomnavigation.BottomNavigationView

    /**
     * here I get the driver Id send by the AuthActivity ( loginFragment or the Signin )
     * we used a bottom nav bar in order to navigate between all the fragment of this Activity :
     * the DriverHistoryFragment ( the list of all the travel with the driverId of this Driver)
     * the AppMainFragment ( the list of all the travel with the Status "SEND")
     * the ProfilFragment who display the details of the DRiver who logIN in this Session
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_app)


        val intent = intent
        driverId = intent.getStringExtra("Driverid")


        menu = findViewById(R.id.bottomNav)
        menu.selectedItemId = R.id.NewFeeds
        /**
         * here we used the bottom nav bar listener in order to navigate between all the fragments
         * */
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