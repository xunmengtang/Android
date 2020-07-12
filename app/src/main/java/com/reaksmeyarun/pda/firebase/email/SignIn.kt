package com.reaksmeyarun.pda.firebase.email

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.connection.FirebaseConnection.Companion.InstanceAuth
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_CANCEL_LISTENER
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_COMPLETE_LISTENER
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_FAILURE_LISTENER
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_SUCCESS_LISTENER
import com.reaksmeyarun.pda.listener.FireBaseListener

class SignIn(val TAG: String, val email: String, val pass: String, val listener: FireBaseListener) {
    fun execute(){
        try{
            InstanceAuth.signInWithEmailAndPassword(email, pass)
                .addOnCanceledListener {
                    Log.e(TAG, ON_CANCEL_LISTENER)
                }.addOnFailureListener {
                    listener.onFailureListener()
                    Log.d(TAG, "$ON_FAILURE_LISTENER : $it")
                }.addOnSuccessListener {
                    Log.d(TAG, "$ON_SUCCESS_LISTENER : $it")
                }.addOnCompleteListener {
                    Log.d(TAG, "$ON_COMPLETE_LISTENER : $it")
                    listener.onCompleteListener(it)
                }
        }catch (ex : Exception){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }
}