package com.reouven.easydriver.ui

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
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.reouven.easydriver.R
import com.reouven.easydriver.entity.Driver
import com.reouven.easydriver.viewmodel.DriverViewModel
import java.util.concurrent.TimeUnit


class Signin : Fragment() {

    var auth = FirebaseAuth.getInstance()
    private lateinit var mAuth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private lateinit var storedVerificationId: String

    lateinit var mail: EditText
    lateinit var password: EditText
    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var telephone: EditText
    lateinit var codeVerification: EditText
    lateinit var driver: Driver
    lateinit var driverId: String
    lateinit var driverViewModel: DriverViewModel
    lateinit var sendCode : Button
    lateinit var verification : Button
    lateinit var Submit : Button

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

        Submit.setOnClickListener {

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

        sendCode.setOnClickListener {
            if(telephone.text.toString() != "")
                startPhoneNumberVerification()
                codeVerification.visibility = view.visibility
                verification.visibility = view.visibility
                sendCode.visibility=View.GONE

        }

        verification.setOnClickListener {
            //faire lauthntification
            verifyPhoneNumberWithCode(storedVerificationId)
        }

        view.findViewById<Button>(R.id.SGoToLogIn).setOnClickListener {
            findNavController().navigate(R.id.action_signin_to_loginFragment)
        }

        view.findViewById<EditText>(R.id.phone_number).setOnClickListener{
            sendCode.visibility = view.visibility
            sendCode.visibility = View.GONE
        }

    }

    private fun initAllData(view: View) {
        mail = view.findViewById<EditText>(R.id.mail)
        password = view.findViewById<EditText>(R.id.password)
        firstName = view.findViewById<EditText>(R.id.firstName)
        lastName = view.findViewById<EditText>(R.id.lastName)
        codeVerification = view.findViewById<EditText>(R.id.code)
        telephone = view.findViewById(R.id.phone_number)
        driverViewModel = DriverViewModel()
        sendCode = view.findViewById <Button>(R.id.SendCode)
        verification = view.findViewById <Button>(R.id.verifaction)
        Submit = view.findViewById <Button>(R.id.SGoToOrder)

        mAuth = FirebaseAuth.getInstance()

        auth = Firebase.auth

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                Toast.makeText(this@Signin.requireActivity(), "code verifier  ", Toast.LENGTH_SHORT).show()
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {

                if (e is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(this@Signin.requireActivity(), "failed ", Toast.LENGTH_SHORT).show()

                } else if (e is FirebaseTooManyRequestsException) {

                }

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                storedVerificationId = verificationId
                //Toast.makeText(this@Signin.requireActivity(), "$verificationId ", Toast.LENGTH_LONG).show()

            }
        }

    }

    private fun checkIfAllNotEmpty(): Boolean =
        firstName.text.toString() != "" &&
                lastName.text.toString() != "" &&
                mail.text.toString() != "" &&
                password.text.toString() != "" &&
                telephone.text.toString() != ""

    private fun createemail(mail: String, password: String) {

        DriverViewModel().Storedriver(mail, password,this.activity).addOnCompleteListener { task ->
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
    private fun startPhoneNumberVerification() {
        // [START start_phone_auth]
        Toast.makeText(this.requireActivity(), "init du phone", Toast.LENGTH_SHORT).show()
        var phone = telephone.text.toString()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone.toString())       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this.requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        // [END start_phone_auth]

    }

    private fun verifyPhoneNumberWithCode(verificationId: String?) {
        // [START verify_with_code]
        val CODE = codeVerification.text.toString()
        val credential = PhoneAuthProvider.getCredential(verificationId!!, CODE.toString())
        // [END verify_with_code]
        //Toast.makeText(this.requireActivity(), "$credential", Toast.LENGTH_LONG).show()

        signInWithPhoneAuthCredential(credential)
    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        DriverViewModel().signInWithCredential(credential)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    //Toast.makeText(this.requireActivity(), "task succesfull ", Toast.LENGTH_SHORT).show()
                    //flag=true
                    val user = task.result?.user
                    Submit.isEnabled=true
                    verification.isEnabled=false
                    codeVerification.isEnabled=false
                    // ...
                } else {
                    // Sign in failed, display a message and update the UI
                    //flag=false
                    Toast.makeText(this.requireActivity(), "task else ", Toast.LENGTH_SHORT).show()

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        //flag=false
                        Toast.makeText(this.requireActivity(), "task exception ", Toast.LENGTH_SHORT).show()

                    }
                }
            }

        //return flag
    }
}