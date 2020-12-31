package com.reouven.easydriver


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class TravelRepo {

    fun getTravelData(): LiveData<MutableList<Travel>> {
        val dataMutable = MutableLiveData<MutableList<Travel>>()
        var database = FirebaseDatabase.getInstance().getReference()
        var query : Query = database.child("Travel").orderByChild("posted_at")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val listData = mutableListOf<Travel>()
                for (snapshot: DataSnapshot in datasnapshot.children) {
                    val travel = snapshot.getValue(Travel::class.java)

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
        var query: Query = database.child("Travel").equalTo("IsFree","Status")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val listData = mutableListOf<Travel>()
                for (snapshot: DataSnapshot in datasnapshot.children) {
                    val travel = snapshot.getValue(Travel::class.java)

                    listData.add(travel!!)
                }
                dataMutable.value = listData
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return dataMutable
    }
    }

//if (travel != null) {
//    travel.Date_depart=snapshot.key.toString()
//}