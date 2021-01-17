package com.reouven.easydriver.ui.fragment.authFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.reouven.easydriver.R
import com.reouven.easydriver.repository.DriverRepository
import com.reouven.easydriver.ui.activity.ContextAppActivity
import com.reouven.easydriver.viewmodel.DriverViewModel
import com.reouven.easydriver.viewmodel.UserViewModel


class LoginFragment : Fragment() {

    private lateinit var mail: EditText
    private lateinit var password: EditText
    private lateinit var driverId: String
    private lateinit var resetPassword: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAll(view)
        setAllButtonAction(view)
    }


    private fun initAll(view: View) {
        mail = view.findViewById<EditText>(R.id.Lmail)
        password = view.findViewById<EditText>(R.id.Lpassword)
        resetPassword = view.findViewById(R.id.resetMdp)
    }

    private fun setAllButtonAction(view: View) {
        resetPassword.setOnClickListener {
            if (DriverViewModel().resetPassword(mail.text.toString()))
                Toast.makeText(
                    this.activity,
                    "mail send, please check your mail",
                    Toast.LENGTH_LONG
                ).show()
        }
        view.findViewById<Button>(R.id.LGoToOrder).setOnClickListener {
            if (notEmpty()) {
                if (isAdmin(mail.text.toString(), password.text.toString()))
                    findNavController().navigate(R.id.action_loginFragment_to_adminActivity)
                else
                    checkUserExist(mail.text.toString(), password.text.toString())
            } else {

                Toast.makeText(this.activity, "please enter data", Toast.LENGTH_LONG).show()
            }
        }
        view.findViewById<Button>(R.id.LGoToSignIn).setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signin)
        }
    }

    private fun isAdmin(mail: String, password: String): Boolean =
        mail == "BOSS" && password == "555"

    private fun notEmpty(): Boolean = mail.text.toString() != "" &&
            password.text.toString() != ""

    private fun checkUserExist(mail: String, password: String) {
        DriverViewModel().SignInDriver(mail, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                driverId = FirebaseAuth.getInstance().uid.toString()
                Toast.makeText(this.activity, "welcome", Toast.LENGTH_LONG).show()
                val intent = Intent(activity, ContextAppActivity::class.java)
                intent.putExtra("Driverid", driverId)
                this.requireActivity().startActivity(intent)
            } else {
                Toast.makeText(this.activity, "mail Or password not valid", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }


}