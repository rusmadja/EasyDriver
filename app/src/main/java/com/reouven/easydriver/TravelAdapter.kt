package com.reouven.easydriver


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TravelAdapter(private val context: Context) : RecyclerView.Adapter<TravelAdapter.ViewHolder>() {

    private val datalist = mutableListOf<Travel>()

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
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var date = itemView.findViewById<TextView>(R.id.date)
        var From = itemView.findViewById<TextView>(R.id.adresseFrom)
        var To = itemView.findViewById<TextView>(R.id.adresseTo)

        fun bindView(travel:Travel){

            date.setText(travel.Date_depart)
            From.setText(travel.adresse_depart)
            To.setText(travel.adresse_arriver)

            itemView.setOnClickListener {

            }
        }
    }

}
