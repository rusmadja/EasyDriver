package com.reouven.easydriver

class Travel (
    val userId:String ="",
    val driverId:String = "",
    var nb_voyageur:String="",
    var adresse_depart:String="",
    var adresse_arriver:String="",
    var Date_depart:String="",
    var Date_arriver:String="",
    var dateReservation:String="",
    var Status: String=""
    )
{
    override fun toString(): String =
                "user:$userId, " +
                "driver: $driverId"+
                "$nb_voyageur, " +
                "$adresse_depart, " +
                "$adresse_arriver, " +
                "$Date_depart, " +
                "$Date_arriver"
}
