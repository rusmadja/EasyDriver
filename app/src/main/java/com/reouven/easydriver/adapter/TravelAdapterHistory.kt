package com.reouven.easydriver.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.reouven.easydriver.R
import com.reouven.easydriver.entity.Travel
import com.reouven.easydriver.viewmodel.TravelViewModel

class TravelAdapterHistory(fragment: Fragment, driverId: String?) :
    RecyclerView.Adapter<TravelAdapterHistory.ViewHolder>() {
    private val datalist = mutableListOf<Travel>()
    var driverId = driverId
    var fragment = fragment
    fun setListData(data: MutableList<Travel>) {
        datalist.clear()
        datalist.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.travel_row_history, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val travel = datalist.get(position)
        holder.bindView(travel)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var departure = itemView.findViewById<TextView>(R.id.datefrom)
        var arrival = itemView.findViewById<TextView>(R.id.dateTo)
        var From = itemView.findViewById<TextView>(R.id.adresseFrom)
        var To = itemView.findViewById<TextView>(R.id.adresseTo)
        var passenger = itemView.findViewById<TextView>(R.id.passenger)
        var buttonValider = itemView.findViewById<Button>(R.id.accepter)


        fun bindView(travel: Travel) {

            departure.setText(travel.Date_depart)
            arrival.setText(travel.Date_arriver)
            From.setText(travel.adresse_depart)
            To.setText(travel.adresse_arriver)
            passenger.setText(travel.nb_voyageur)


            if (travel.Status == "CLOSE") {
                buttonValider.isEnabled = false
                buttonValider.setText("closed")
            }

            buttonValider.setOnClickListener {
                if (driverId != null)
                    TravelViewModel().UpdateDriverId(travel, driverId!!)
                val bundle = bundleOf("travel" to travel.toString())
                NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_driverHistoriyTravelFragment2_to_inRoadFragment2, bundle)
            }

        }
    }

}
