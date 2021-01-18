package com.reouven.easydriver.ui.fragment.appFragment

import android.content.Context
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
import com.reouven.easydriver.adapter.TravelAdapterHistory
import com.reouven.easydriver.ui.activity.ContextAppActivity
import com.reouven.easydriver.viewmodel.TravelViewModel

/**
 * theAppMainFragment allow the Driver to see a list of all the Travel with the DriverId "his driver ID"
 * he can begin a travel and close it
 * */
class DriverHistoriyTravelFragment : Fragment() {

    /** variables declarations  */
    lateinit var activity: ContextAppActivity
    lateinit var adapter: TravelAdapterHistory
    lateinit var recyclerView: RecyclerView
    lateinit var driverId: String
    val viewModel: TravelViewModel by lazy {
        ViewModelProviders.of(this).get(TravelViewModel::class.java)
    }

    /** onCreateView : basic function of the fragment */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        driverId = activity.driverId
        return inflater.inflate(R.layout.fragment_driver_historiy_travel, container, false)
    }

    /** onViewCreated : the function that will execute all the fragment action */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /***
         * we init all the variables : the adapter with the TravelAdapterHistory
         * the recyclerView with binding
         * after all the init we call the observer()
         */
        adapter = TravelAdapterHistory(this, driverId)
        recyclerView = view.findViewById(R.id.recycler1)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        observer()

    }

    /** onAttach : basic function of the fragment that allows us to get the DriverId that we defined in the ContextAppActivity ,
     * this DriverId was send by the LoginFragment or the SignInFragment
     * we can use this variable in every fragment of this ContextAppActivity*/
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as ContextAppActivity
    }

    /**
     * function observer()
     * this fonction was called after all the init
     * call the ViewModel in order to set the list of all Travels
     * call the setListData from the adapter to set the list
     * notify the adapter that Data changed
     */
    fun observer() {
        viewModel.fetchUserDatabyDriverid(driverId).observe(this.requireActivity(), Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }


}