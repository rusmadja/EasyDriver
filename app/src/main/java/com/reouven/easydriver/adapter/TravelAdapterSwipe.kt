package com.reouven.easydriver.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reouven.easydriver.R
import com.reouven.easydriver.entity.Travel

class TravelAdapterSwipe(private val context: Context) : RecyclerView.Adapter<TravelAdapterSwipe.ViewHolder>() {

    private val datalist = mutableListOf<Travel>()

    fun setListData(data:MutableList<Travel>){
        datalist.clear()
        datalist.addAll(data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(context).inflate(R.layout.travel_row,parent,false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val travel = datalist.get(position)
        holder.bindView(travel)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var departure = itemView.findViewById<TextView>(R.id.datefrom)
        var arrival = itemView.findViewById<TextView>(R.id.dateTo)
        var From = itemView.findViewById<TextView>(R.id.adresseFrom)
        var To = itemView.findViewById<TextView>(R.id.adresseTo)
        var passenger = itemView.findViewById<TextView>(R.id.passenger)

        fun bindView(travel: Travel){

            departure.setText(travel.Date_depart)
            arrival.setText(travel.Date_arriver)
            From.setText(travel.adresse_depart)
            To.setText(travel.adresse_arriver)
            passenger.setText(travel.nb_voyageur)
            itemView.setOnClickListener {

            }
        }
    }

}
