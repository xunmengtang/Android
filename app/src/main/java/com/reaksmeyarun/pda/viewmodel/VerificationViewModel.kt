package com.reaksmeyarun.pda.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.reaksmeyarun.pda.datamodel.CodeSentDataModel
import com.reaksmeyarun.pda.firebaseRepo.phone_number.VerifyVerificationCode
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import com.reaksmeyarun.pda.view.activity.Z0300VerificationActivity
import kotlinx.android.synthetic.main.activity_z0300_verification.*

class VerificationViewModel (val activity : Z0300VerificationActivity) : ViewModel(){
    private val TAG = "VerificationViewModel"
    fun handleVerification(view : View){
        if(!isOTPValidation())
            return
        Log.d(TAG, "${CodeSentDataModel.verificationId}")
        VerifyVerificationCode(CodeSentDataModel.verificationId!!, activity.etOTP.text.toString()).apply {
            onFirebaseListener(object : FireBaseListener{
                override fun onFailureListener() {  /* Do nothing*/ }

                override fun <TResult> onCompleteListener(task: Task<TResult>) {
                    when{
                        task.isSuccessful -> activity.startActivity(activity, P0200HomeActivity::class.java)
                        task.isComplete -> {    /* TODO : */    }
                        task.isCanceled -> {    /* TODO : */    }
                    }
                }
            })
        }.execute()
    }
    fun handleResendVerification(view : View){
        if(CodeSentDataModel.verificationId.isNullOrEmpty())
            return
    }
    private fun isOTPValidation() : Boolean = when{
        activity.etOTP.text.toString().isBlank() -> {
            activity.etOTP.error = "Please fill the OTP"
            false
        }activity.etOTP.text.toString().length>6 ->{
            activity.etOTP.error = "Only 6 digits"
            false
        }
        else -> {
            activity.etOTP.error = null
            true
        }
    }
}