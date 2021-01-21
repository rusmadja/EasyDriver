package com.reouven.easydriver.ui.fragment.authFragment

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

/** Sign in Fragment : this Fragment will allows the Driver to Create an account.*/
class Signin : Fragment() {

    /** variables declarations  */

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
    lateinit var sendCode: Button
    lateinit var verification: Button
    lateinit var Submit: Button

    /** onCreateView : basic function of the fragment */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    /** onViewCreated : the function that will execute all the fragment action */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAllData(view)
        setAllButtonClick(view)

    }

    /** init all the declarations and do all the binding we need
     * mail(textview) , password(textview) , firstname(textview) , lastname(textview) ,telephone(textview) , driverViewModel(UserViewModel())
     * in this fragment we used the phone verification so we add :
     * a phone number(textview) , a codeVerification(textview) , a SendCode(Button) , a Verification(Button)
     * also we init a callbacks in order to be able to send and check the verificationCode sended on the phone of the Driver
     * */
    private fun initAllData(view: View) {
        mail = view.findViewById<EditText>(R.id.mail)
        password = view.findViewById<EditText>(R.id.password)
        firstName = view.findViewById<EditText>(R.id.firstName)
        lastName = view.findViewById<EditText>(R.id.lastName)
        codeVerification = view.findViewById<EditText>(R.id.code)
        telephone = view.findViewById(R.id.phone_number)
        driverViewModel = DriverViewModel()
        sendCode = view.findViewById<Button>(R.id.SendCode)
        verification = view.findViewById<Button>(R.id.verifaction)
        Submit = view.findViewById<Button>(R.id.SGoToOrder)

        mAuth = FirebaseAuth.getInstance()

        auth = Firebase.auth

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                Toast.makeText(this@Signin.requireActivity(), "code verifier  ", Toast.LENGTH_SHORT)
                    .show()
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {

                if (e is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(this@Signin.requireActivity(), "failed ", Toast.LENGTH_SHORT)
                        .show()

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

    /** called all the buttons listener of the SigninFragment  */
    private fun setAllButtonClick(view: View) {

        /** call Submit button : Check if the fields are fulfil after that call the function createemail
         * in order to register the Driver in the application
         * after all the driver will be register in firebase authentication and in the realTime Database.*/
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

        /** call SendCode button : Check if the fields are fulfil after that call the function startPhoneNumberVerification()
         * in order to send the Verification Code on the Driver's Phone
         * after all the EditText for typing the code is open and the button Verification too
         * the Button send Code disappear*/
        sendCode.setOnClickListener {
            if (telephone.text.toString() != "")
                startPhoneNumberVerification()
            codeVerification.visibility = view.visibility
            verification.visibility = view.visibility
            sendCode.visibility = View.GONE
        }

        /** call verification button : call the function verifyPhoneNumberWithCode(storedVerificationId)
         * in order to check the Code
         * the storedVerificationId was init in the callbacks in the function initAllData()
         */
        verification.setOnClickListener {
            verifyPhoneNumberWithCode(storedVerificationId)
        }


        /** call "LogIN if you already have an account"  buttons : navigate to the logIn fragment
         * where the driver can login */
        view.findViewById<Button>(R.id.SGoToLogIn).setOnClickListener {
            findNavController().navigate(R.id.action_signin_to_loginFragment)
        }

        /** the listener of the textView of the phoneNumber , after fulfill this field the sendCode Button appear */
        view.findViewById<EditText>(R.id.phone_number).setOnClickListener {
            sendCode.visibility = view.visibility
            //sendCode.visibility = View.GONE
        }

    }

    /** check if fields in the login screen are fulfil  */
    private fun checkIfAllNotEmpty(): Boolean =
        firstName.text.toString() != "" &&
                lastName.text.toString() != "" &&
                mail.text.toString() != "" &&
                password.text.toString() != "" &&
                telephone.text.toString() != ""


    /** this fonction call the StoreDriver from the DriverViewModel and after a task.isSuccessful (true)
     * get the driverId created by Firebase and init the variable "driverId" with it.
     * after we Create driver with createDriver()
     * we call the setDriverinFirebse from the driverViewModel to store it in the realTime database.
     * we pass this driverId to the ContextAppActivity (using the bundle) in order to fulfil the Travel propertie : driverID when a Driver
     * will accept the Travel he want
     * if task not succesfull , a toast will be send */
    private fun createemail(mail: String, password: String) {

        DriverViewModel().Storedriver(mail, password, this.activity).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                driverId = FirebaseAuth.getInstance().uid.toString()
                Toast.makeText(this.activity, "welcome", Toast.LENGTH_LONG).show()
                createDriver()
                driverViewModel.setdriverInFirebase(driver)
                val bundle = bundleOf("driverId" to driverId)
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

    /** create the driver after using all the bindings   */
    private fun createDriver() {
        driver = Driver(
            driverId,
            firstName.text.toString(),
            lastName.text.toString(),
            mail.text.toString(),
            telephone.text.toString()
        )
    }

    /** this fonction call the PhoneAuthProvider from Firebase after created the PhoneAuthOptions.
     * after all call the verifyPhoneNumber */
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

    /** this fonction called by the button verification from the signin fragment
     * get the code from the binding and use it in the credential
     * after all call the function signInWithPhoneAuthCredential(credential)
     * */
    private fun verifyPhoneNumberWithCode(verificationId: String?) {
        // [START verify_with_code]
        val CODE = codeVerification.text.toString()
        val credential = PhoneAuthProvider.getCredential(verificationId!!, CODE.toString())
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential)
    }

    /** this fonction call signInWithCredential(credential) from DriverViewModel()
     * if everything is verified, enable the visibility of the submit Button and let the Driver create his Account
     * */
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        DriverViewModel().signInWithCredential(credential)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    Submit.isEnabled = true
                    verification.isEnabled = false
                    codeVerification.isEnabled = false
                } else {
                    // Sign in failed, display a message and update the UI
                    Toast.makeText(this.requireActivity(), "task else ", Toast.LENGTH_SHORT).show()

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(
                            this.requireActivity(),
                            "task exception ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
    }
}