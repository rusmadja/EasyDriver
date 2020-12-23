package com.reouven.easydriver

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DriverRepository() {
    val reference = FirebaseDatabase.getInstance().getReference("Driver")

    fun setDriverInFirebase(Driver: HashMap<String, String>, DriverId: String) {
        //val reference = FirebaseDatabase.getInstance().getReference("Driver")
        reference.child(DriverId).setValue(Driver)
    }

    fun SignIn(mail: String, password: String) =
        FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, password)

    fun UpdateDriverInFirebase(Driver: Driver) {
        Driver
        TODO()
    }
    fun StoreDriver(mail:String, password:String) = FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, password)

    fun resetPassword(mail: String) {
        var Driver = FirebaseAuth.getInstance()
        Driver.sendPasswordResetEmail(mail)
    }
}