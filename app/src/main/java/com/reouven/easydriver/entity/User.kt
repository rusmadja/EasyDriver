package com.reouven.easydriver.entity


data class User(
    val id: String,
    val firstName: String = "Default Name",
    val lastName: String = "Default Name",
    var mail: String = "Default mail",
    var telephone: String = "Default Number"
)
