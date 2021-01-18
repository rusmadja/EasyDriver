package com.reouven.easydriver.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.reouven.easydriver.entity.Travel

/**
 * the Class TravelRepository
 * This class is called from theTravelViewModel in order to respect the MVVM architecture
 * this class call the firebase functions
 * */
class TravelRepository {

    /** the getLiveDataTravelListByStatus called by the view model in order to get all the travel by status
     * this functon call the realtime database , do a snapshot of all the database and return a list of travels by status send by the viewModel */
    fun getLiveDataTravelListByStatus(status: String): LiveData<MutableList<Travel>> {
        val dataMutable = MutableLiveData<MutableList<Travel>>()
        var database = FirebaseDatabase.getInstance().getReference()
        var query: Query = database.child("Travel").orderByChild("Status").equalTo(status)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val listData = mutableListOf<Travel>()
                for (snapshot: DataSnapshot in datasnapshot.children) {
                    val travel = snapshot.getValue(Travel::class.java)
                    if (travel != null) {
                        travel.travelId = snapshot.key.toString()
                    }
                    listData.add(travel!!)
                }
                listData.reverse()
                dataMutable.value = listData
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return dataMutable
    }

    /** the getTravelDatabyDriverId called by the view model in order to get all the travel by driverId
     * this function call the realtime database , do a snapshot of all the database and return a list of travels by driverId send by the viewModel */
    fun getTravelDatabyDriverId(driverId: String): LiveData<MutableList<Travel>> {
        val dataMutable = MutableLiveData<MutableList<Travel>>()
        var database = FirebaseDatabase.getInstance().getReference()
        var query: Query = database.child("Travel").orderByChild("driverId").equalTo(driverId)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val listData = mutableListOf<Travel>()
                for (snapshot: DataSnapshot in datasnapshot.children) {
                    val travel = snapshot.getValue(Travel::class.java)
                    if (travel != null) {
                        travel.travelId = snapshot.key.toString()
                    }
                    listData.add(travel!!)
                }
                listData.reverse()
                dataMutable.value = listData

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return dataMutable

    }

    /** the UpdateDriverId called by the view model , send a driverId and a travel , in order to set
     * this triverId of this travel in the realtime database  */
    fun UpdateDriverId(travel: Travel, driverId: String) {
        var reference = FirebaseDatabase.getInstance().getReference("Travel")
        reference.child(travel.travelId).child("driverId").setValue(driverId)

    }

    /** the UpdateStatus called by the view model , send a status and a travel , in order to set
     * this status of this travel in the realtime database  */
    fun UpdateStatus(travel: Travel, status: String) {
        var reference = FirebaseDatabase.getInstance().getReference("Travel")
        reference.child(travel.travelId).child("Status").setValue(status)
        if (status == "SEND")
            reference.child(travel.travelId).child("driverId").setValue("null")
    }

}

