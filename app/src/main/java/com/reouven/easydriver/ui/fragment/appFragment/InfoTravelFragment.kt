package com.reouven.easydriver.ui.fragment.appFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.reouven.easydriver.R
/**
 * InfoTravelFragment : this fragment is called after a click on an item of the list
 * display all the details about a travel
 */
class InfoTravelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_travel, container, false)
    }

    /**
     * the value set by the bundle send by the TravelAdapter.
     * in this value there is all the data of the travel ( travel.toString() )
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var value = arguments?.getString("travel").toString()

        view.findViewById<TextView>(R.id.all_info).setText(value)

        /**
         * this button return to the main list
         */
        view.findViewById<Button>(R.id.returnToAllData).setOnClickListener {
            findNavController().navigate(R.id.action_infoTravelFragment_to_appMainFragment)
        }
    }
}