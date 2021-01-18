package com.reouven.easydriver.viewmodel


import androidx.lifecycle.ViewModel
import com.reouven.easydriver.entity.User
import com.reouven.easydriver.repository.UserRepository

/**
 * !!!!!!!!!!!!!  IN THIS APP WE DON'T USE THIS CLASS  !!!!!!!!!!!!!
 *
 * the Class UserViewModel
 * This class is called from the fragment in order to respect the MVVM architecture
 * this class create the HashMap that will be send to the DataBase
 *
 * */
class UserViewModel() : ViewModel() {
    /** variables declarations */
    private val UserRepo = UserRepository()

    /** the SignIn function called by the LogIN fragment in order to authenticate the User
     * this function Called the function SignIN from the UserRepository()*/
    fun SignInUser(mail: String, password: String) = UserRepo.SignIn(mail, password)

    /** the resetPassword function called by the LogIN fragment in order to reset the User's password
     * this function Called the function resetPassword from the UserRepository()*/
    fun resetPassword(mail: String): Boolean {
        if (mail != "") {
            UserRepo.resetPassword(mail)
            return true
        }
        else
            return false
    }

    fun updateUser(user: User) = UserRepo.UpdateUserInFirebase(user)

    /** the StoreUser function called by the SignIN fragment in order to register (create) a User in the Database authentication
     * this function Called the function StoreUser from the UserRepository()
     *
     */
    fun StoreUser (mail: String, password: String)= UserRepo.StoreUser(mail, password)

    /** the setUserInFirebase function called by the SignIN fragment in order to register (create) a User in the Database realtime
     * this function Called the function setUserInFirebase from the UserRepository()*/
    fun setUserInFirebase(user: User) {

        var hsmap: HashMap<String, String> = hashMapOf()

        hsmap.put("first_name", user.firstName)
        hsmap.put("last_name", user.lastName)
        hsmap.put("mail", user.mail)
        hsmap.put("telephone", user.telephone)

        UserRepo.setUserInFirebase(hsmap, user.id)

    }

    /*fun setUser(user: User){
    UserRepo.setUserInFirebase(user)}*/

}