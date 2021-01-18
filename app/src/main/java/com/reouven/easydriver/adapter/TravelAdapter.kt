package com.reouven.easydriver.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.reouven.easydriver.R
import com.reouven.easydriver.entity.Travel
import com.reouven.easydriver.viewmodel.TravelViewModel

/**
 * the TravelAdapter Class :
 * this class adapt our Travel as we build it in the travel_row.xml(graphical part)
 */
class TravelAdapter(fragment: Fragment, driverId: String?) :
    RecyclerView.Adapter<TravelAdapter.ViewHolder>() {
    /** variables declarations  */
    private val datalist = mutableListOf<Travel>()
    var driverId = driverId
    var fragment = fragment

    /**
     * function setListData :
     * this function clear the list (in order to have a empty list before every read of the Database)
     * init the list with the data read in the Database (Travel)
     */
    fun setListData(data: MutableList<Travel>) {
        datalist.clear()
        datalist.addAll(data)
    }

    /**
     * function onCreateViewHolder :
     * this function init the view with the travel_row.xml(graphical part)
     * return the view Holder with this view that was init
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.travel_row_newfeed, parent, false)

        return ViewHolder(view)
    }

    /**
     * function onBindViewHolder :
     * this function called the viewHolder and give him the Travel in position x that we chosen
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val travel = datalist.get(position)
        holder.bindView(travel)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    /**
     * the inner ViewHolder Class :
     * this class display on the itemView every data that we want :
     * in this case we display all the details of the travel received in the constructor
     * set every items of the list
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * all the binding of the travel_row.xml
         */
        var departure = itemView.findViewById<TextView>(R.id.datefrom)
        var arrival = itemView.findViewById<TextView>(R.id.dateTo)
        var From = itemView.findViewById<TextView>(R.id.adresseFrom)
        var To = itemView.findViewById<TextView>(R.id.adresseTo)
        var passenger = itemView.findViewById<TextView>(R.id.passenger)
        var buttonValider = itemView.findViewById<Button>(R.id.accepter)

        /**
         * set the Textview of the Travel_row.xml with all the data from the travel we get in the Constructor
         */
        fun bindView(travel: Travel) {

            departure.setText(travel.Date_depart)
            arrival.setText(travel.Date_arriver)
            From.setText(travel.adresse_depart)
            To.setText(travel.adresse_arriver)
            passenger.setText(travel.nb_voyageur)

            /**
             * this button is on every item of the list.
             * when you click on an travel Item a fragment with all the details of this travelItem will open
             */
            itemView.setOnClickListener {
                val bundle = bundleOf("travel" to travel.toString())

                findNavController(fragment).navigate(
                    R.id.action_appMainFragment_to_infoTravelFragment,
                    bundle
                )
            }

            /**
             * this button is on every item of the list.
             * when you click on this button, call the TravelViewModel in order to Update the Status Of the Travel from "SEND" to "RECEIVE"
             * and set the DriverId of this Travel.
             * after all send the travel to the InRoadfragment
             */
            buttonValider.setOnClickListener {
                TravelViewModel().UpdateToReceive(travel)

                if (driverId != null)
                    TravelViewModel().UpdateDriverId(travel, driverId!!)

            }

        }
    }

}
