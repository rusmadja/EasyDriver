package com.reouven.easydriver.entity

/**
 * the class Driver :
 * the Driver will get a ListView of all the travels with the Status "SEND" and will
 * be able to select the travel he want.
 * He will contact the User that created this travel for business talks
 * He has an Id , a firstName , a lastName,  a Phone_Number and a Mail*/
data class Driver(
    val id: String,
    val firstName: String = "Default Name",
    val lastName: String = "Default Name",
    var mail: String = "Default mail",
    var telephone: String = "Default Number"
)
