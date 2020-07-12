package com.reaksmeyarun.pda.firebase.email

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.connection.FirebaseConnection.Companion.InstanceAuth
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.listener.FireBaseListener

class SignUp(val TAG: String, val email: String, val pass: String, val listener: FireBaseListener) {
    fun execute(){
        try{
            InstanceAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCanceledListener {
                    Log.e(TAG, FirebaseConstance.ON_CANCEL_LISTENER)
                }.addOnFailureListener {
                    listener.onFailureListener()
                    Log.e(TAG, "${FirebaseConstance.ON_FAILURE_LISTENER} : $it")
                }.addOnSuccessListener {
                    Log.d(TAG, "${FirebaseConstance.ON_SUCCESS_LISTENER} : $it")
                }.addOnCompleteListener {
                    Log.d(TAG, "${FirebaseConstance.ON_COMPLETE_LISTENER} : $it")
                    listener.onCompleteListener(it)
                }
        }catch (ex : Exception){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION} : $ex")
        }catch (exceptionInInitializerError : ExceptionInInitializerError){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $exceptionInInitializerError")
        }
    }
}