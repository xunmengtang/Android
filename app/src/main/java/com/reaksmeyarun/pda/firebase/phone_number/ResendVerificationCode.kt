package com.reaksmeyarun.pda.firebase.phone_number

import android.util.Log
import com.google.firebase.auth.PhoneAuthProvider
import com.reaksmeyarun.pda.base.BaseFirebase
import com.reaksmeyarun.pda.datamodel.CodeSentDataModel
import com.reaksmeyarun.pda.view.activity.Z0300VerificationActivity
import java.lang.Exception
import java.util.concurrent.TimeUnit

class ResendVerificationCode (var phoneNumber : String, val activity : Z0300VerificationActivity) : BaseFirebase(){
    private val TAG = "ResendVerificationCode"
    private lateinit var onVerificationStateChangedCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    fun onVerificationStateChanged(onVerificationStateChangedCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks){
        this.onVerificationStateChangedCallbacks = onVerificationStateChangedCallbacks
    }

    fun execute(){
        try{
            instancePhoneAuth.verifyPhoneNumber(
                phoneNumber, // Phone number to verify
                120, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                activity, // Activity (for callback binding)
                onVerificationStateChangedCallbacks, // OnVerificationStateChangedCallbacks
                CodeSentDataModel.token) // ForceResendingToken from callbacks
        }catch (ex : Exception){
            Log.e(TAG, "Exception : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "ExceptionInInitializerError : $ex")
        }
    }
}