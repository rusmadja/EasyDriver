package com.reouven.easydriver.util

/**
 * the Status is an enum class : this is the status of the travel
 * 1 the user create a travel on the application 1 => save the travel on the data base with the status "SEND"
 * 2 the driver accept a travel => update this travel to "RECEIVE"
 * 3 the driver come to the daparture adresse, get the User, when the user is in the car : the driver begin the travel => update to "ON_ROAD"
 * 4 when the driver arrive to the arrival adress , the Driver clode the travel => update to "CLOSE"
 */
enum class Status {
    SEND,
    RECEIVE,
    ON_ROAD,
    CLOSE
}
