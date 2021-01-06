package com.reouven.easydriver.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.reouven.easydriver.entity.User

class UserRepository() {
    val reference = FirebaseDatabase.getInstance().getReference("User")

    fun setUserInFirebase(user: HashMap<String, String>, userId: String) {
        //val reference = FirebaseDatabase.getInstance().getReference("User")
        reference.child(userId).setValue(user)
    }

    fun SignIn(mail: String, password: String) =
        FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, password)

    fun UpdateUserInFirebase(user: User) {
        user
        TODO()
    }
    fun StoreUser(mail:String, password:String) = FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, password)

    fun resetPassword(mail: String) {
        var user = FirebaseAuth.getInstance()
        user.sendPasswordResetEmail(mail)
    }
}