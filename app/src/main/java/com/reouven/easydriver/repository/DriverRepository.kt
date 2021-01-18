package com.reouven.easydriver.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.reouven.easydriver.entity.Driver

/**
 * the Class DriverRepository
 * This class is called from theDriverViewModel in order to respect the MVVM architecture
 * this class call the firebase functions
 * */
class DriverRepository() {

    /** the firebase reference */
    val reference = FirebaseDatabase.getInstance().getReference("Driver")
    var auth = Firebase.auth

    /** the setDriverInFirebase function called by the DriverViewModel in order to register (create) a Driver in the Database realtime
     * this function set the value of the Driver in the DataBase , the child of the reference "Driver" will be the DriverId
     * and the childs of this DriverId will be all the properties that the haspMap contain
     * */
    fun setDriverInFirebase(Driver: HashMap<String, String>, DriverId: String) {
        reference.child(DriverId).setValue(Driver)
    }

    /** the SignIn function called by the driverViewModel in order to logIN a Driver to the application
     * this function Called the function signInWithEmailAndPassword from the Firebase itself and if the result task is succesfull
     * the Driver exist in the Database and can enter in the application
     * */
    fun SignIn(mail: String, password: String) =
        FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, password)

    fun UpdateDriverInFirebase(Driver: Driver) {
        Driver
        TODO()
    }

    /** the StoreDriver function called by the driverViewModel in order to register (create) a Driver in the Database authentication
     * this function Called the function createUserWithEmailAndPassword from the Firebase itself*/
    fun StoreDriver(mail: String, password: String) =
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, password)

    /** the resetPassword function called by the driverViewModel in order to send a mail to the driver who forgot his password
     * this function Called the function sendPasswordResetEmai from the Firebase itself*/
    fun resetPassword(mail: String) {
        var Driver = FirebaseAuth.getInstance()
        Driver.sendPasswordResetEmail(mail)
    }

    /** the signInWithCredential function called by the driverViewModel in order to register (create) a Driver in the Database authentication
     * with Credential ( phoneNumber ) after sending a code to the phone Number and after checking the code the Driver will be SignIn with his
     * phone number
     * this function Called the function signInWithCredential from the Firebase itself*/
    fun signInWithCredential(credential: PhoneAuthCredential) =
        auth.signInWithCredential(credential)


/*=========================================================================================================*/
/*===========================ajouter ici un get driver info en fonction du id==============================*/
/*=========================================================================================================*/
/*=========================================================================================================*/

}