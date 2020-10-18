package com.reaksmeyarun.pda.firebase.phone_number

import android.util.Log
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.auth.PhoneAuthProvider
import com.reaksmeyarun.pda.base.BaseFirebase
import java.lang.Exception
import java.util.concurrent.TimeUnit

class SendVerificationCode(var phoneNumber : String) : BaseFirebase() {
    private val TAG = "SendVerificationCode"
    private lateinit var onVerificationStateChangedCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    fun verificationStateChangedCallbacks(onVerificationStateChangedCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks){
        this.onVerificationStateChangedCallbacks = onVerificationStateChangedCallbacks
    }
    fun execute(){
        try {
            instancePhoneAuth
                .verifyPhoneNumber(
                    phoneNumber,
                    120,
                    TimeUnit.SECONDS,
                    TaskExecutors.MAIN_THREAD,
                    onVerificationStateChangedCallbacks
                )
        }catch (ex : Exception){
            Log.e(TAG, "$ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "$ex")
        }
    }
}