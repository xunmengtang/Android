package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.firebase.FirebaseEmit
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.utils.EmailValidator
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.view.activity.Z0300SignUpActivity
import kotlinx.android.synthetic.main.activity_z0300_sign_up.*
import kotlinx.android.synthetic.main.activity_z0300_sign_up.etEmail

class SignUpViewModel(val activity : Z0300SignUpActivity) : ViewModel() {
    private val TAG = "SignUpViewModel"
    private val firebaseEmit = FirebaseEmit()
    fun handleBackPress(view : View){
        activity.finish()
    }
    fun handleSignUp(view : View){
        if(!isValidation())
            return
        activity.progressing.visibility = View.VISIBLE
        firebaseEmit.signUp(TAG, FirebaseConnection.firebaseAuth, activity.etEmail.text.toString(), activity.etPassword.text.toString(),
            object : FireBaseListener{
                override fun onFailureListener() {
                    PopupMsg.alert(activity, activity.getString(R.string.msg_email_exiting))
                }
                override fun <TResult> onCompleteListener(task: Task<TResult>) {
                    when{
                        task.isSuccessful -> {
                            firebaseEmit.sendEmailVerification(TAG, FirebaseConnection.firebaseAuth,
                                object : FireBaseListener{
                                    override fun onFailureListener() {
                                        PopupMsg.alert(activity, activity.getString(R.string.msg_cant_send_email_verification))
                                    }

                                    override fun <TResult> onCompleteListener(task: Task<TResult>) {
                                        when{
                                            task.isSuccessful -> {
                                                PopupMsg.alert(activity, activity.getString(R.string.msg_send_verity_email),
                                                    object : PopupMsg.OnClickButtonCloseCallBack{
                                                        override fun onCloseCallBack() {
                                                            FirebaseConnection.firebaseAuth.signOut()
                                                            activity.finish()
                                                        }
                                                    })
                                            }
                                            task.isCanceled -> PopupMsg.alert(activity, activity.getString(R.string.msg_something_wrong))
                                        }
                                    }
                                })
                        }task.isCanceled -> PopupMsg.alert(activity, activity.getString(R.string.msg_something_wrong))
                    }
                }
            })
        activity.progressing.visibility = View.GONE
    }
    private fun isValidation() : Boolean{
        return when{
            activity.etEmail.text!!.isBlank() -> {
                activity.etEmail.error = "Fill Your Email Here"
                false
            }!EmailValidator.isEmailValid(activity.etEmail.text.toString()) -> {
                activity.etEmail.error = "******@gmail.com"
                false
            }activity.etPassword.text!!.length < 6 -> {
                activity.etPassword.error = "At Least 6 Characters"
                false
            }activity.etConPassword.text!!.length < 6 -> {
                activity.etConPassword.error = "At Least 6 Characters"
                false
            }activity.etConPassword.text!!.equals(activity.etPassword.text!!) ->{
                activity.etPassword.error = "Password mis-match"
                activity.etConPassword.error = "Password mis-match"
                false
            }else -> {
                activity.etEmail.error = null
                activity.etPassword.error = null
                activity.etConPassword.error = null
                true
            }
        }
    }
}