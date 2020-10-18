package com.reaksmeyarun.pda.firebase.email

import android.util.Log
import com.reaksmeyarun.pda.base.BaseFirebase
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.listener.FireBaseListener

class SignUp(val TAG: String, val email: String, val pass: String, val listener: FireBaseListener) : BaseFirebase() {
    fun execute(){
        try{
            instanceAuth.createUserWithEmailAndPassword(email, pass)
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