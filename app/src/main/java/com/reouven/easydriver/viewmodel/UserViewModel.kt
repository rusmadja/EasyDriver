package com.reouven.easydriver.viewmodel


import androidx.lifecycle.ViewModel
import com.reouven.easydriver.entity.User
import com.reouven.easydriver.repository.UserRepository

class UserViewModel() : ViewModel() {
    private val UserRepo = UserRepository()

    /*fun setUser(user: User){
        UserRepo.setUserInFirebase(user)
    }*/
    fun SignInUser(mail: String, password: String) = UserRepo.SignIn(mail, password)

    fun resetPassword(mail: String): Boolean {
        if (mail != "") {
            UserRepo.resetPassword(mail)
            return true
        }
        else
            return false
    }

    fun updateUser(user: User) {
        UserRepo.UpdateUserInFirebase(user)
    }
    fun StoreUser (mail: String, password: String)= UserRepo.StoreUser(mail, password)

    fun setUserInFirebase(user: User) {

        var hsmap: HashMap<String, String> = hashMapOf()

        hsmap.put("first_name", user.firstName)
        hsmap.put("last_name", user.lastName)
        hsmap.put("mail", user.mail)
        hsmap.put("telephone", user.telephone)

        UserRepo.setUserInFirebase(hsmap, user.id)

    }
}