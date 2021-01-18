package com.reouven.easydriver.entity

/**
 * the class Travel
 * a Travel can be created by a User.
 * The Travel will be display to the Driver screen , and he will be able to select the Travel he wants
 * A travel is Created in 2 parts :
 * first in OrderFragment , second in OrderFragment2
 * the Travel has : UserId (the User who created this Travel)
 * DriverId( the driver who accepted this travel)
 * number of traveler
 * adresse of departure
 * adresse of arrival
 * date of departure
 * date of arrival
 * date of reservation (the date when the Travel was Store to the DataBase
 * and a Status : SEND , RECEIVE , ON_ROAD , CLOSE
 * */
class Travel (
    val userId:String ="",
    val driverId:String = "",
    var nb_voyageur:String="",
    var adresse_depart:String="",
    var adresse_arriver:String="",
    var Date_depart:String="",
    var Date_arriver:String="",
    var dateReservation:String="",
    var Status: String="",
    var travelId:String = "",

    )
{
    override fun toString(): String =
                //"$userId, " + "$driverId,"+ "$nb_voyageur, " + "$adresse_depart, " + "$adresse_arriver, " + "$Date_depart, " + "$Date_arriver,"+ "$travelId"


         "$userId, " + "$driverId,"+ "$nb_voyageur, " + "$adresse_depart, " + "$adresse_arriver, " + "$Date_depart, " + "$Date_arriver,"+ "$dateReservation,"+ "$Status,"+ "$travelId"


}
