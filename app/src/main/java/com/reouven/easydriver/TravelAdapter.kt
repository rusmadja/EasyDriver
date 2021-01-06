package com.reouven.easydriver


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TravelAdapter(private val context: Context, driverId: String) : RecyclerView.Adapter<TravelAdapter.ViewHolder>() {

    private val datalist = mutableListOf<Travel>()
    var driverId = driverId
    fun setListData(data:MutableList<Travel>){
        datalist.clear()
        datalist.addAll(data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(context).inflate( R.layout.travel_row,parent,false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val travel = datalist.get(position)
        holder.bindView(travel)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {

        var departure = itemView.findViewById<TextView>(R.id.datefrom)
        var arrival = itemView.findViewById<TextView>(R.id.dateTo)
        var From = itemView.findViewById<TextView>(R.id.adresseFrom)
        var To = itemView.findViewById<TextView>(R.id.adresseTo)
        var passenger = itemView.findViewById<TextView>(R.id.passenger)
        var buttonValider = itemView.findViewById<Button>(R.id.accepter)


        fun bindView(travel:Travel){

            departure.setText(travel.Date_depart)
            arrival.setText(travel.Date_arriver)
            From.setText(travel.adresse_depart)
            To.setText(travel.adresse_arriver)
            passenger.setText(travel.nb_voyageur)


            itemView.setOnClickListener {
                Toast.makeText(context,"${travel.toString()}",Toast.LENGTH_LONG).show()
            }
            buttonValider.setOnClickListener {

                TravelViewModel().UpdateToReceive(travel)
                TravelViewModel().UpdateDriverId(travel,driverId)
            }

        }
    }

}
