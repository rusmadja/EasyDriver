package com.reouven.easydriver


import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.PhoneAuthCredential

class DriverViewModel() : ViewModel() {
    private val DriverRepo = DriverRepository()
    
    fun SignInDriver(mail: String, password: String) = DriverRepo.SignIn(mail, password)
    fun signInWithCredential(credential: PhoneAuthCredential) =DriverRepo.signInWithCredential(credential)


    fun resetPassword(mail: String): Boolean {
        if (mail != "") {
            DriverRepo.resetPassword(mail)
            return true
        }
        else
            return false
    }

    fun updateDriver(driver: Driver) {
        DriverRepo.UpdateDriverInFirebase(driver)
    }
    fun Storedriver (mail: String, password: String, activity: FragmentActivity?)= DriverRepo.StoreDriver(mail, password)

    fun setdriverInFirebase(driver: Driver) {

        var hsmap: HashMap<String, String> = hashMapOf()

        hsmap.put("first_name", driver.firstName)
        hsmap.put("last_name", driver.lastName)
        hsmap.put("mail", driver.mail)
        hsmap.put("telephone", driver.telephone)

        DriverRepo.setDriverInFirebase(hsmap, driver.id)

    }


}