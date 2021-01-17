package com.reouven.easydriver.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.reouven.easydriver.entity.Travel

class TravelRepository {

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

    fun UpdateDriverId(travel: Travel, driverId: String) {
        var reference = FirebaseDatabase.getInstance().getReference("Travel")
        reference.child(travel.travelId).child("driverId").setValue(driverId)

    }

    fun UpdateStatus(travel: Travel, status: String) {
        var reference = FirebaseDatabase.getInstance().getReference("Travel")
        reference.child(travel.travelId).child("Status").setValue(status)
        if (status == "SEND")
            reference.child(travel.travelId).child("driverId").setValue("null")
    }

}

