package com.reouven.easydriver.entity

/**
 * the Class User
 * The User can create a Travel (an order )
 * this Travel will be send to the Driver in the other application (EASY DRIVER)
 * he has : first name
 * last name
 * mail
 * telephone number
 * */
data class User(
    var id: String = "null",
    var first_name: String = "Default Name",
    var last_name: String = "Default Name",
    var mail: String = "Default mail",
    var telephone: String = "Default Number"
) {

    override fun toString(): String {
        return "User(id='$id', firstName='$first_name', lastName='$last_name', mail='$mail', telephone='$telephone')"
    }
}

