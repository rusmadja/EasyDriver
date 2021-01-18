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
import com.reouven.easydriver.adapter.TravelAdapter
import com.reouven.easydriver.ui.activity.ContextAppActivity
import com.reouven.easydriver.viewmodel.TravelViewModel

/**
 * theAppMainFragment allow the Driver to see all a list of all the Travel with the Status "SEND"
 * he can accept a travel and/or see all the Travel's details
 * */
class AppMainFragment : Fragment() {

    /** variables declarations  */
    lateinit var adapter: TravelAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var driverId :String
    lateinit var activity: ContextAppActivity

    val viewModel: TravelViewModel by lazy { ViewModelProviders.of(this).get(TravelViewModel::class.java) }

    /** onAttach : basic function of the fragment that allows us to get the DriverId that we defined in the ContextAppActivity ,
     * this DriverId was send by the LoginFragment or the SignInFragment
     * we can use this variable in every fragment of this ContextAppActivity*/
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as ContextAppActivity
    }

    /** onCreateView : basic function of the fragment */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        driverId = activity.driverId
        return inflater.inflate(R.layout.fragment_app_main, container, false)
    }

    /** onViewCreated : the function that will execute all the fragment action */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        /***
         * we init all the variables : the adapter with the TravelAdapter
         * the recyclerView with binding
         * after all the init we call the observer()
         */
        //initialise ici le driver id
        adapter = TravelAdapter(this,driverId)

        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        observer()
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * function observer()
     * this fonction was called after all the init
     * call the ViewModel in order to set the list of all Travels
     * notify the adapter that Data changed
     */
    fun observer(){
        viewModel.fetchSENDUserData().observe(this.requireActivity(), Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

}