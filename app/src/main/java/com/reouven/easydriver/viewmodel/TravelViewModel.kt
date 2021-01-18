package com.reouven.easydriver.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reouven.easydriver.entity.Travel
import com.reouven.easydriver.repository.TravelRepository

/**
 * the Class TravelViewModel
 * This class is called from the fragment in order to respect the MVVM architecture
 * this class call the TravelReopsitory in order to read in the database and update travels
 * */
class TravelViewModel : ViewModel() {
    /** variables declarations */
    val repo = TravelRepository()

    /** the fetchClosesTravelData function called by the AdminDashboardfragment in order to display a list of all travel with the Status "CLOSE"
     * call the TravelRepository() in order to call the database and return all the Close travels in a MutableList<Travel>
     */
    fun fetchClosesTravelData(): LiveData<MutableList<Travel>> {
        val mutableData = MutableLiveData<MutableList<Travel>>()
        repo.getLiveDataTravelListByStatus("CLOSE").observeForever { userlist ->
            mutableData.value = userlist
        }
        return mutableData
    }

    /** the fetchSENDUserData function called by the AppMainFragment in order to display a list of all travel with the Status "SEND"
     * call the TravelRepository() in order to call the database and return all the "SEND" travels in a MutableList<Travel>
     */
    fun fetchSENDUserData(): LiveData<MutableList<Travel>> {
        val mutableData = MutableLiveData<MutableList<Travel>>()
        repo.getLiveDataTravelListByStatus("SEND").observeForever { userlist ->
            mutableData.value = userlist
        }
        return mutableData
    }

    /** the fetchUserDatabyDriverid function called by the DriverHistoryFragment in order to display a list of all travel with the DriverId
     * "the driver id of the driver who logIn"
     * call the TravelRepository() in order to call the database and return all the "driver.DriverID" travels in a MutableList<Travel>
     */
    fun fetchUserDatabyDriverid(driverId: String): LiveData<MutableList<Travel>> {
        val mutableData = MutableLiveData<MutableList<Travel>>()
        repo.getTravelDatabyDriverId(driverId).observeForever { userlist ->
            mutableData.value = userlist
        }
        return mutableData
    }

    /** the UpdateToReceive function called by the AppMainFragment after that a driver accepted a travel of the list
     * call the TravelRepository() in order to update the status of this travel
    */
    fun UpdateToReceive(travel: Travel) {
        repo.UpdateStatus(travel, "RECEIVE")
    }

    fun UpdateDriverId(travel: Travel, driverId: String) {
        repo.UpdateDriverId(travel, driverId)
    }

    /** the UpdateToSEND function not called in this application
     * call the TravelRepository() in order to update the status of this travel
     */
    fun UpdateToSEND(travel: Travel) {
        repo.UpdateStatus(travel, "SEND")
    }

    /** the UpdateToONROAD function called by the InRoadFragment() after that a driver click on "Start Road"
     * call the TravelRepository() in order to update the status of this travel
     */
    fun UpdateToONROAD(travel: Travel) {
        repo.UpdateStatus(travel, "ONROAD")
    }

    /** the UpdateToCLOSE function called by the InRoadFragment() after that a driver click on "Finish Succesfull"
     * call the TravelRepository() in order to update the status of this travel
     */
    fun UpdateToCLOSE(travel: Travel) {
        repo.UpdateStatus(travel, "CLOSE")
    }

}
