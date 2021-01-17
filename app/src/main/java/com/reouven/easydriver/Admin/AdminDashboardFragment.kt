package com.reouven.easydriver.Admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reouven.easydriver.R
import com.reouven.easydriver.viewmodel.TravelViewModel

class AdminDashboardFragment : Fragment() {

    lateinit var adapter: TravelAdapterAdmin
    lateinit var recyclerView: RecyclerView
    lateinit var driverId: String
    val viewModel: TravelViewModel by lazy {
        ViewModelProviders.of(this).get(TravelViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialise ici le driver id
        adapter = TravelAdapterAdmin(this, null)

        recyclerView = view.findViewById(R.id.recycleradmin)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        observer()
    }

    fun observer() {
        viewModel.fetchClosesTravelData().observe(this.requireActivity(), Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }


}