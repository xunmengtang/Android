package com.reaksmeyarun.pda.firebase.phone_number

import android.util.Log
import com.google.firebase.auth.PhoneAuthProvider
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.listener.FireBaseListener

class VerifyVerificationCode (var verificationId : String, var otpCode : String){
    private val TAG = "VerifyVerificationCode"
    private lateinit var fireBaseListener : FireBaseListener
    fun onFirebaseListener(fireBaseListener: FireBaseListener){
        this.fireBaseListener = fireBaseListener
    }
    fun execute(){
        try{
            FirebaseConnection.InstanceAuth.signInWithCredential(PhoneAuthProvider.getCredential(verificationId, otpCode))
                .addOnCanceledListener {
                    Log.e(TAG, "addOnCanceledListener")
                }.addOnFailureListener(){
                    Log.d(TAG, "addOnFailureListener : $it")
                    fireBaseListener.onFailureListener()
                }.addOnSuccessListener {
                    Log.d(TAG, "addOnSuccessListener : $it")
                }.addOnCompleteListener {
                    Log.d(TAG, "addOnCompleteListener : $it")
                    fireBaseListener.onCompleteListener(it)
                }
        }catch (ex : Exception){
            Log.e(TAG, "Exception : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "ExceptionInInitializerError : $ex")
        }
    }
}