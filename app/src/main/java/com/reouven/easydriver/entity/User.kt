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
    val id: String,
    val firstName: String = "Default Name",
    val lastName: String = "Default Name",
    var mail: String = "Default mail",
    var telephone: String = "Default Number"
)
