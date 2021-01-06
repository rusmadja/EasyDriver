package com.reouven.easydriver

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

    private fun setAllButtonAction(view: View) {
        resetPassword.setOnClickListener {
            if (UserViewModel().resetPassword(mail.text.toString()))
                Toast.makeText(
                    this.activity,
                    "mail send, please check your mail",
                    Toast.LENGTH_LONG
                ).show()
        }


        view.findViewById<Button>(R.id.LGoToOrder).setOnClickListener {
            if (notEmpty()) {
                checkUserExist(mail.text.toString(), password.text.toString())
            } else {

                Toast.makeText(this.activity, "please enter data", Toast.LENGTH_LONG).show()
            }
        }
        view.findViewById<Button>(R.id.LGoToSignIn).setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_signin)

        }
    }

    private fun initAll(view: View) {
        mail = view.findViewById<EditText>(R.id.Lmail)
        password = view.findViewById<EditText>(R.id.Lpassword)
        resetPassword = view.findViewById(R.id.resetMdp)
    }

    private fun checkUserExist(mail: String, password: String) {

        DriverViewModel().SignInDriver(mail, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                driverId = FirebaseAuth.getInstance().uid.toString()
                //val bundle = bundleOf("driverId" to driverId)
                Toast.makeText(this.activity, "welcome", Toast.LENGTH_LONG).show()
                /*findNavController().navigate(
                    R.id.action_loginFragment_to_contextAppActivity,
                    bundle
                )*/
                val intent = Intent(activity, ContextAppActivity::class.java)
                intent.putExtra("Driverid", driverId)
                this.requireActivity().startActivity(intent)

            } else {
                Toast.makeText(this.activity, "mail Or password not valid", Toast.LENGTH_LONG)
                    .show()
            }

        }

    }

    private fun notEmpty(): Boolean = mail.text.toString() != "" &&
            password.text.toString() != ""

}