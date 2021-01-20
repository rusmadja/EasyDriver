package com.reouven.easydriver.ui.fragment.appFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.*
import com.reouven.easydriver.R
import com.reouven.easydriver.entity.Travel
import com.reouven.easydriver.entity.User
import com.reouven.easydriver.ui.activity.ContextAppActivity
import java.util.*
import kotlin.random.Random.Default.nextInt

class ProfilFragment : Fragment() {

    lateinit var activity: ContextAppActivity
    lateinit var driverId: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as ContextAppActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        driverId=activity.driverId
        Toast.makeText(this.context,driverId,Toast.LENGTH_LONG).show()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var query: Query =  FirebaseDatabase.getInstance().getReference("Driver").child(driverId)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if (datasnapshot.exists()) {
                    view.findViewById<TextView>(R.id.userMail)
                        .setText(datasnapshot.child("mail").value.toString())
                    view.findViewById<TextView>(R.id.userFirstName).setText(
                        datasnapshot.child("first_name").value.toString() +"  "+ datasnapshot.child("last_name").value.toString()
                    )
                    view.findViewById<TextView>(R.id.userPhone).setText(datasnapshot.child("telephone").value.toString())

                }
                else{
                    var query: Query =  FirebaseDatabase.getInstance().getReference("User").child(driverId)
                    query.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            if (datasnapshot.exists()) {
                                view.findViewById<TextView>(R.id.userMail)
                                    .setText(datasnapshot.child("mail").value.toString())
                                view.findViewById<TextView>(R.id.userFirstName).setText(
                                    datasnapshot.child("first_name").value.toString() +"  "+ datasnapshot.child("last_name").value.toString()
                                )
                                view.findViewById<TextView>(R.id.userPhone).setText(datasnapshot.child("telephone").value.toString())

                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                        }
                    })
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        view.findViewById<RatingBar>(R.id.ratingBar).setRating((0..5).random().toFloat())


    }
}

