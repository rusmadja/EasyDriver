package com.reouven.easydriver.ui.fragment.appFragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.*
import com.reouven.easydriver.R
import com.reouven.easydriver.entity.Travel
import com.reouven.easydriver.entity.User
import com.reouven.easydriver.viewmodel.TravelViewModel
import com.reouven.easydriver.viewmodel.UserViewModel

/**
 * InfoTravelFragment : this fragment is called after a click on an item of the list
 * display all the details about a travel
 */
class InfoTravelFragment : Fragment() {
     var user: User?=null
    val viewModel: UserViewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }
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

        var lists:MutableList<User>? = mutableListOf()
        var value = arguments?.getString("travel").toString()
        var list = value.split(",") as MutableList<String>
        var travel = Travel(list[0], list[1], list[2], list[3], list[4], list[5], list[6], list[7],list[8],list[9])


        var query: Query =  FirebaseDatabase.getInstance().getReference("User").child(travel.userId)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
              view.findViewById<TextView>(R.id.userFirstName).setText(datasnapshot.child("first_name").value.toString())
              view.findViewById<TextView>(R.id.userLastName).setText(datasnapshot.child("last_name").value.toString())
              view.findViewById<TextView>(R.id.userMail).setText(datasnapshot.child("mail").value.toString())
              view.findViewById<TextView>(R.id.userPhone).setText(datasnapshot.child("telephone").value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        view.findViewById<TextView>(R.id.TravelDeparture).setText(travel.adresse_depart)
        view.findViewById<TextView>(R.id.TravelArrival).setText(travel.adresse_arriver)
        view.findViewById<TextView>(R.id.TravelDateDeparture).setText(travel.Date_depart)
        view.findViewById<TextView>(R.id.TravelDateArrival).setText(travel.Date_arriver)
        view.findViewById<TextView>(R.id.TravelID).setText(travel.travelId)

        /**
         * this button return to the main list
         */
        view.findViewById<Button>(R.id.returnToAllData).setOnClickListener {
            findNavController().navigate(R.id.action_infoTravelFragment_to_appMainFragment)
        }

    }
}