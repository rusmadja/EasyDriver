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

/**
 * the Login Fragment allow the Driver to LogIN to the the application if his account already exist
 * */
class LoginFragment : Fragment() {

    /** variables  declarations : */
    private lateinit var mail: EditText
    private lateinit var password: EditText
    private lateinit var driverId: String
    private lateinit var resetPassword: Button

    /** onCreateView : basic function of the fragment */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    /** onViewCreated : the function that will execute all the fragment action */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAll(view)
        setAllButtonAction(view)
    }

    /** init all the declarations and do all the binding we need
     * mail(textview) , password(textview) , resetPassword(button)*/
    private fun initAll(view: View) {
        mail = view.findViewById<EditText>(R.id.Lmail)
        password = view.findViewById<EditText>(R.id.Lpassword)
        resetPassword = view.findViewById(R.id.resetMdp)
    }

    /** called all the buttons listener of the loginFragment  */
    private fun setAllButtonAction(view: View) {

        /** call resetPassword buttons : If  the Driver Forgot his password of this account he can reset it.
         *  call the function resetPassword from the DriverViewModel
         */
        resetPassword.setOnClickListener {
            if (DriverViewModel().resetPassword(mail.text.toString()))
                Toast.makeText(
                    this.activity,
                    "mail send, please check your mail",
                    Toast.LENGTH_LONG
                ).show()
        }

        /** call LOGIN buttons : Check if the fields are fulfil after that call the function CheckUserExist
         * in order to know if this driver can logIn to the application */
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

        /** call "SignIN if you don't have an account"  buttons : navigate to the signIn fragment
         * and the driver can create an account */
        view.findViewById<Button>(R.id.LGoToSignIn).setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signin)
        }
    }

    /** check if fields in the login screen are fulfil with an admin coordinate */
    private fun isAdmin(mail: String, password: String): Boolean =
        mail == "BOSS" && password == "555"

    /** check if fields in the login screen are fulfil  */
    private fun notEmpty(): Boolean = mail.text.toString() != "" &&
            password.text.toString() != ""


    /** this fonction call the SignInDriver from the DriverViewModel and after a task.isSuccessful (true)
     * get the DriverId created by Firebase and init the variable "driverId" with it
     * and pass this driverId to the ContextAppActivity (using the bundle) in order to fulfil the Travel propertie : driverID when a Driver
     * will accept the Travel he want
     * if task not succesfull , a toast will be send */
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