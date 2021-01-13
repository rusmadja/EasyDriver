package com.reouven.easydriver.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reouven.easydriver.entity.Travel
import com.reouven.easydriver.repository.TravelRepository

class TravelViewModel : ViewModel() {
    val repo = TravelRepository()
    fun fetchUserData(): LiveData<MutableList<Travel>> {
        val mutableData = MutableLiveData<MutableList<Travel>>()
        repo.getFreeTravel().observeForever { userlist ->
            mutableData.value = userlist
        }
        return mutableData
    }
    fun fetchUserDatabyDriverid(driverId: String): LiveData<MutableList<Travel>> {
        val mutableData = MutableLiveData<MutableList<Travel>>()
        repo.getTravelDatabyDriverId(driverId).observeForever { userlist ->
            mutableData.value = userlist
        }
        return mutableData
    }
    fun UpdateToReceive(travel: Travel)
    {
        repo.UpdateToReceive(travel)
    }

    fun UpdateDriverId(travel: Travel, driverId: String) {
        repo.UpdateDriverId(travel,driverId)
    }

    fun UpdateToSEND(travel: Travel) {
        repo.UpdateToSEND(travel)
    }

    fun UpdateToONROAD(travel: Travel) {
        repo.UpdateToONROAD(travel)
    }
    fun UpdateToCLOSE(travel: Travel) {
        repo.UpdateToCLOSE(travel)
    }

    fun fetchAllUserData(): LiveData<MutableList<Travel>>  {
        val mutableData = MutableLiveData<MutableList<Travel>>()
        repo.getonRoadTravelData().observeForever { userlist ->
            mutableData.value = userlist
        }
        return mutableData
    }
}
