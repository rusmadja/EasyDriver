package com.reouven.easydriver.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.reouven.easydriver.entity.Travel
import com.reouven.easydriver.entity.User
import kotlin.math.log

/**
 * the Class UserRepository
 * This class is called from the userViewModel in order to respect the MVVM architecture
 * this class call the firebase functions
 * */
class UserRepository() {
    /** the firebase reference */
    val reference = FirebaseDatabase.getInstance().getReference("User")

    /** the setUserInFirebase function called by the userViewModel in order to register (create) a User in the Database realtime
     * this function Called set the value of the User in the DataBase the child of the reference "User" will be the UserId
     * and the childs of this UserId will be all the properties that the haspMap contain*/
    fun setUserInFirebase(user: HashMap<String, String>, userId: String) {
        reference.child(userId).setValue(user)
    }

    /** the SignIn function called by the userViewModel in order to logIN a User to the application
     * this function Called the function signInWithEmailAndPassword from the Firebase itself and if the result task is succesfull
     * the User exist in the Database*/
    fun SignIn(mail: String, password: String) =
        FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, password)

    fun UpdateUserInFirebase(user: User) {
        user
        TODO()
    }

    /** the StoreUser function called by the userViewModel in order to register (create) a User in the Database authentication
     * this function Called the function createUserWithEmailAndPassword from the Firebase itself*/
    fun StoreUser(mail: String, password: String) =
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, password)

    /** the resetPassword function called by the userViewModel in order to send a mail to the User who forgot his password
     * this function Called the function sendPasswordResetEmai from the Firebase itself*/
    fun resetPassword(mail: String) {
        var user = FirebaseAuth.getInstance()
        user.sendPasswordResetEmail(mail)
    }

    fun getUserById(userId: String): User {
        lateinit var user:User
        var database = FirebaseDatabase.getInstance().getReference()
        var query: Query = database.child("User")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for (snapshot: DataSnapshot in datasnapshot.children) {
                    if(snapshot.key==userId)
                    user = snapshot.getValue(User::class.java)!!
                }


            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
        return user
    }
    /*=========================================================================================================*/
    /*===========================ajouter ici un get user info en fonction du id================================*/
    /*=========================================================================================================*/
    /*=========================================================================================================*/

}