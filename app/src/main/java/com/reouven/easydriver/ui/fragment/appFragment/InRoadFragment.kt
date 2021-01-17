package com.reouven.easydriver.ui.fragment.appFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.reouven.easydriver.R
import com.reouven.easydriver.entity.Travel
import com.reouven.easydriver.viewmodel.TravelViewModel

class InRoadFragment : Fragment() {

    lateinit var travel: Travel
    lateinit var value: String
    lateinit var list: MutableList<String>
    lateinit var finish: Button
    lateinit var start: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_road, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        value = arguments?.getString("travel").toString()
        list = value.split(",") as MutableList<String>
        travel = Travel(list[0], list[1], list[2], list[3], list[4], list[5], list[6], list[7],list[8],list[9])
        finish = view.findViewById<Button>(R.id.finishRoad)
        start = view.findViewById<Button>(R.id.onRoad)
        start.setOnClickListener {
            TravelViewModel().UpdateToONROAD(travel)
            finish.isEnabled = true
            start.isEnabled = false
        }
        finish.setOnClickListener {
            findNavController().navigate(R.id.action_inRoadFragment2_to_driverHistoriyTravelFragment2)
            TravelViewModel().UpdateToCLOSE(travel)
        }
        view.findViewById<Button>(R.id.annule_road).setOnClickListener {
            findNavController().navigate(R.id.action_inRoadFragment2_to_driverHistoriyTravelFragment2)
            TravelViewModel().UpdateToReceive(travel)
        }
    }

}