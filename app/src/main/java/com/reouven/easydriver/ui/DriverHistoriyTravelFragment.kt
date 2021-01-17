package com.reouven.easydriver.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reouven.easydriver.R
import com.reouven.easydriver.adapter.TravelAdapterSwipe
import com.reouven.easydriver.viewmodel.TravelViewModel

class DriverHistoriyTravelFragment : Fragment() {

    lateinit var  activity: ContextAppActivity
    lateinit var adapter: TravelAdapterSwipe
    lateinit var recyclerView: RecyclerView
    lateinit var driverId :String
    val viewModel: TravelViewModel by lazy { ViewModelProviders.of(this).get(TravelViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        driverId = activity.driverId
        return inflater.inflate(R.layout.fragment_driver_historiy_travel, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.backToMain).setOnClickListener {
            findNavController().navigate(R.id.action_driverHistoriyTravelFragment_to_appMainFragment)
        }


        //initialise ici le driver id
        adapter = TravelAdapterSwipe(this,driverId)

        recyclerView = view.findViewById(R.id.recycler1)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        observer()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as ContextAppActivity
    }

    fun observer(){
        viewModel.fetchUserDatabyDriverid(driverId).observe(this.requireActivity(), Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }


}