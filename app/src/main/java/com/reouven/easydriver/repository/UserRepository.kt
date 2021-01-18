package com.reouven.easydriver.repository

import android.content.ContentValues.TAG
import android.util.Log
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
    lateinit var uservaluestring: String
    var uservalue: User? = null
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

    fun getUserById(userId: String): LiveData<MutableList<User>> {

        val dataMutable = MutableLiveData<MutableList<User>>()
        var database = FirebaseDatabase.getInstance().getReference()
        var query: Query = database.child("User").orderByKey().equalTo(userId)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val listData = mutableListOf<User>()
                for (snapshot: DataSnapshot in datasnapshot.children) {
                    lateinit var user: User
                    if (snapshot.exists()) {
                        user = snapshot.getValue(User::class.java)!!
                        user!!.id = snapshot.key.toString()
                    }
                    listData.add(user!!)
                }
                listData.reverse()
                dataMutable.value = (listData)

            }



            override fun onCancelled(error: DatabaseError) {

            }
        })

        return dataMutable

    }

    /*=========================================================================================================*/
    /*===========================ajouter ici un get user info en fonction du id================================*/
    /*=========================================================================================================*/
    /*=========================================================================================================*/


}