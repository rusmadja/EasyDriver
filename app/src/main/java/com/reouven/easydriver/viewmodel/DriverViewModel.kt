package com.reouven.easydriver.viewmodel


import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.PhoneAuthCredential
import com.reouven.easydriver.entity.Driver
import com.reouven.easydriver.repository.DriverRepository

/**
 * the Class DriverViewModel
 * This class is called from the fragment in order to respect the MVVM architecture
 * this class create the HashMap that will be send to the DataBase
 * */
class DriverViewModel() : ViewModel() {
    /** variables declarations */
    private val DriverRepo = DriverRepository()

    /** the SignInDriver function called by the LogIN fragment in order to authenticate the Driver
     * this function Called the function SignIN from the DriverRepository()*/
    fun SignInDriver(mail: String, password: String) = DriverRepo.SignIn(mail, password)

    /** the signInWithCredential function called by the SignIN fragment in order to create a Driver account
     * this function Called the function signInWithCredential from the DriverRepository()
     * its an account with his PhoneNumber */
    fun signInWithCredential(credential: PhoneAuthCredential) =
        DriverRepo.signInWithCredential(credential)

    /** the Storedriver function called by the SignIN fragment in order to register (create) a Driver in the Database authentication
     * this function Called the function StoreDriver from the DriverRepository()
     * its an account with a Mail and a Password*/
    fun Storedriver(mail: String, password: String, activity: FragmentActivity?) =
        DriverRepo.StoreDriver(mail, password)

    /** the setdriverInFirebase function called by the SignIN fragment in order to register (create) a User in the Database realtime
     * this function Called the function setdriverInFirebase from the DriverRepository()
     */
    fun setdriverInFirebase(driver: Driver) {

        var hsmap: HashMap<String, String> = hashMapOf()

        hsmap.put("first_name", driver.firstName)
        hsmap.put("last_name", driver.lastName)
        hsmap.put("mail", driver.mail)
        hsmap.put("telephone", driver.telephone)

        DriverRepo.setDriverInFirebase(hsmap, driver.id)

    }

    /** the resetPassword function called by the LogIN fragment in order to reset the Driver's password
     * this function Called the function resetPassword from the DriverRepository()*/
    fun resetPassword(mail: String): Boolean {
        if (mail != "") {
            DriverRepo.resetPassword(mail)
            return true
        } else
            return false
    }

    fun updateDriver(driver: Driver) = DriverRepo.UpdateDriverInFirebase(driver)


}