package com.reouven.easydriver

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TravelViewModel : ViewModel() {
    val repo = TravelRepo()
    fun fetchUserData(): LiveData<MutableList<Travel>> {
        val mutableData = MutableLiveData<MutableList<Travel>>()
        repo.getFreeTravel().observeForever { userlist ->
            mutableData.value = userlist
        }
        return mutableData
    }
    fun UpdateToReceive(travel:Travel)
    {
        repo.UpdateToReceive(travel)
    }

    fun UpdateDriverId(travel:Travel,driverId: String) {
        repo.UpdateDriverId(travel,driverId)
    }
}
