package com.reaksmeyarun.pda.firebaseRepo.email

import android.util.Log
import com.reaksmeyarun.pda.base.BaseFirebase
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.listener.FireBaseListener

class ResetPassword(val TAG : String ?= "", val email : String ?= "", val listener : FireBaseListener) : BaseFirebase(){
    fun execute(){
        try{
            instanceAuth.sendPasswordResetEmail(email!!)
                .addOnCanceledListener {
                    Log.e(TAG, FirebaseConstance.ON_CANCEL_LISTENER)
                }.addOnFailureListener {
                    Log.e(TAG, "${FirebaseConstance.ON_FAILURE_LISTENER} : $it")
                    listener.onFailureListener()
                }.addOnCompleteListener {
                    Log.i(TAG, "${FirebaseConstance.ON_COMPLETE_LISTENER} : $it")
                    listener.onCompleteListener(it)
                }.addOnSuccessListener {
                    Log.i(TAG, "${FirebaseConstance.ON_SUCCESS_LISTENER} : $it")
                }
        }catch (ex : Exception){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION} : $ex")
        }catch (exceptionInInitializerError : ExceptionInInitializerError){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $exceptionInInitializerError")
        }
    }
}