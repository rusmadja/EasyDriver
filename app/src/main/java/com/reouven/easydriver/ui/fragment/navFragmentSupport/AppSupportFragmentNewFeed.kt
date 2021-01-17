package com.reouven.easydriver.ui.fragment.navFragmentSupport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reouven.easydriver.R

class AppSupportFragmentNewFeed: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.app_support_nav_newfeed, container, false)
    }
}