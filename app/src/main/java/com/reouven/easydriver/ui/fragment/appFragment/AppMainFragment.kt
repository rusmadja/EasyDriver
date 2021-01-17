package com.reouven.easydriver.ui.fragment.appFragment

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
import com.reouven.easydriver.adapter.TravelAdapter
import com.reouven.easydriver.viewmodel.TravelViewModel

class AppMainFragment : Fragment() {

    lateinit var adapter: TravelAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var driverId :String
    val viewModel: TravelViewModel by lazy { ViewModelProviders.of(this).get(TravelViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_main, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //initialise ici le driver id
        adapter = TravelAdapter(this,null)

        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        observer()
        super.onViewCreated(view, savedInstanceState)
    }

    fun observer(){
        viewModel.fetchSENDUserData().observe(this.requireActivity(), Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

}