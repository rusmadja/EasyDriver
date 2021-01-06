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
    fun UpdateToReceive(travel: Travel)
    {
        repo.UpdateToReceive(travel)
    }

    fun UpdateDriverId(travel: Travel, driverId: String) {
        repo.UpdateDriverId(travel,driverId)
    }
}
