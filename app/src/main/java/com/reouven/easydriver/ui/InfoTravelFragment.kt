package com.reouven.easydriver.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.reouven.easydriver.R

class InfoTravelFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_travel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var value = arguments?.getString("travel").toString()

        view.findViewById<TextView>(R.id.all_info).setText(value)

        view.findViewById<Button>(R.id.returnToAllData).setOnClickListener {
            findNavController().navigate(R.id.action_infoTravelFragment_to_appMainFragment)
        }
    }

}