package com.reouven.easydriver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.google.firebase.auth.FirebaseAuth


class Signin : Fragment() {

    lateinit var mail: EditText
    lateinit var password: EditText
    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var telephone: EditText
    lateinit var driver: Driver
    lateinit var driverId: String
    lateinit var driverViewModel: DriverViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_signin, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAllData(view)

        setAllButtonClick(view)

    }

    private fun setAllButtonClick(view: View) {

        view.findViewById<Button>(R.id.SGoToOrder).setOnClickListener {

            if (checkIfAllNotEmpty()) {
                createemail(mail.text.toString(), password.text.toString())
            } else {
                Toast.makeText(
                    this.activity,
                    "please enter data",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        view.findViewById<Button>(R.id.LGoToLogIn).setOnClickListener {
            findNavController().navigate(R.id.action_signin_to_loginFragment)
        }

    }

    private fun initAllData(view: View) {
        mail = view.findViewById<EditText>(R.id.mail)
        password = view.findViewById<EditText>(R.id.password)
        firstName = view.findViewById<EditText>(R.id.firstName)
        lastName = view.findViewById<EditText>(R.id.lastName)
        telephone = view.findViewById(R.id.phone_number)
        driverViewModel = DriverViewModel()

    }

    private fun checkIfAllNotEmpty(): Boolean =
        firstName.text.toString() != "" &&
                lastName.text.toString() != "" &&
                mail.text.toString() != "" &&
                password.text.toString() != "" &&
                telephone.text.toString() != ""

    private fun createemail(mail: String, password: String) {

        UserViewModel().StoreUser(mail, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                 driverId = FirebaseAuth.getInstance().uid.toString()
                Toast.makeText(this.activity, "welcome", Toast.LENGTH_LONG).show()
                createDriver()
                driverViewModel.setdriverInFirebase(driver)
                val bundle = bundleOf("driverId" to driverId )
                findNavController().navigate(
                    R.id.action_signin_to_contextAppActivity,
                    bundle
                )
            } else {
                Toast.makeText(
                    this.activity,
                    "this mail is already use for an account",
                    Toast.LENGTH_LONG
                ).show()

            }
        }
    }

    private fun createDriver() {
        driver = Driver(
            driverId,
            firstName.text.toString(),
            lastName.text.toString(),
            mail.text.toString(),
            telephone.text.toString()
        )
    }
}