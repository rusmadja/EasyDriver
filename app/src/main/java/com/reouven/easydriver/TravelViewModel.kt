package com.reouven.easydriver

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TravelViewModel : ViewModel() {
    val repo = TravelRepo()
    fun fetchUserData(): LiveData<MutableList<Travel>> {
        val mutableData = MutableLiveData<MutableList<Travel>>()
        repo.getUserData().observeForever { userlist ->
            mutableData.value = userlist
        }
        return mutableData
    }
}
