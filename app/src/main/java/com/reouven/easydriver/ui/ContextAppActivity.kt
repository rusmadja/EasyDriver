package com.reouven.easydriver.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reouven.easydriver.R
import com.reouven.easydriver.adapter.TravelAdapter
import com.reouven.easydriver.viewmodel.TravelViewModel


class ContextAppActivity : AppCompatActivity() {
    lateinit var adapter: TravelAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var driverId :String

    val viewModel: TravelViewModel by lazy { ViewModelProviders.of(this).get(TravelViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_app)
        val intent = intent
        driverId = intent.getStringExtra("Driverid")

        adapter = TravelAdapter(this,driverId)

        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        observer()

    }
    fun observer(){
        viewModel.fetchUserData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}