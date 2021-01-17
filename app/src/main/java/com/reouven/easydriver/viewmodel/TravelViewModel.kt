package com.reouven.easydriver.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reouven.easydriver.entity.Travel
import com.reouven.easydriver.repository.TravelRepository

class TravelViewModel : ViewModel() {
    val repo = TravelRepository()

    fun fetchClosesTravelData(): LiveData<MutableList<Travel>> {
        val mutableData = MutableLiveData<MutableList<Travel>>()
        repo.getLiveDataTravelListByStatus("CLOSES").observeForever { userlist ->
            mutableData.value = userlist
        }
        return mutableData
    }

    fun fetchSENDUserData(): LiveData<MutableList<Travel>> {
        val mutableData = MutableLiveData<MutableList<Travel>>()
        repo.getLiveDataTravelListByStatus("SEND").observeForever { userlist ->
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

    fun UpdateToReceive(travel: Travel) {
        repo.UpdateStatus(travel, "RECEIVE")
    }

    fun UpdateDriverId(travel: Travel, driverId: String) {
        repo.UpdateDriverId(travel, driverId)
    }

    fun UpdateToSEND(travel: Travel) {
        repo.UpdateStatus(travel, "SEND")
    }

    fun UpdateToONROAD(travel: Travel) {
        repo.UpdateStatus(travel, "ONROAD")
    }

    fun UpdateToCLOSE(travel: Travel) {
        repo.UpdateStatus(travel, "CLOSE")
    }

}
