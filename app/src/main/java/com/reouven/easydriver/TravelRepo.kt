package com.reouven.easydriver


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class TravelRepo {

    fun getTravelData(): LiveData<MutableList<Travel>> {
        val dataMutable = MutableLiveData<MutableList<Travel>>()
        var database = FirebaseDatabase.getInstance().getReference()
        var query : Query = database.child("Travel")
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
                dataMutable.value = listData

            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
        return dataMutable

    }
    fun getFreeTravel(): LiveData<MutableList<Travel>> {
        val dataMutable = MutableLiveData<MutableList<Travel>>()
        var database = FirebaseDatabase.getInstance().getReference()
        var query: Query = database.child("Travel").orderByChild("Status").equalTo("SEND")
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
                dataMutable.value = listData
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return dataMutable
    }
    fun UpdateToReceive(travel:Travel) {
        var reference = FirebaseDatabase.getInstance().getReference("Travel")
        reference.child(travel.travelId).child("Status").setValue("RECEIVE")
    }

        fun UpdateDriverId(travel:Travel,driverId: String) {
        var reference = FirebaseDatabase.getInstance().getReference("Travel")
        reference.child(travel.travelId).child("driverId").setValue(driverId)

    }
}

