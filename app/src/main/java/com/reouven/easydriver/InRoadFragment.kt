package com.reouven.easydriver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class InRoadFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_road, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.annule_road).setOnClickListener {
            findNavController().navigate(R.id.action_inRoadFragment_to_appMainFragment)
            //ajouter changement de status
        }
        view.findViewById<Button>(R.id.finishRoad).setOnClickListener {
            findNavController().navigate(R.id.action_inRoadFragment_to_appMainFragment)
            //ajouter changement de status
        }


    }

}