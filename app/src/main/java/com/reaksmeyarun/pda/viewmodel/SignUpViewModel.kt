package com.reaksmeyarun.pda.viewmodel

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.firebaseRepo.email.SendEmailVerification
import com.reaksmeyarun.pda.firebaseRepo.email.SignUp
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.utils.AlertUtil
import com.reaksmeyarun.pda.utils.EmailValidator
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import com.reaksmeyarun.pda.view.activity.Z0400SignUpActivity
import kotlinx.android.synthetic.main.activity_z0400_sign_up.*

class SignUpViewModel(val activity : Z0400SignUpActivity) : ViewModel() {
    private val TAG = "SignUpViewModel"
    fun handleBackPress(view: View) {
        activity.onBackPressed()
    }

    fun handleSignUp(view: View) {
        if (isValidation()){
            SignUp(TAG,
                activity.binding.etEmail.text.toString(),
                activity.binding.etPassword.text.toString(),
                object : FireBaseListener {
                    override fun onFailureListener() {
                        AlertUtil.init(activity, activity.getString(R.string.msg_email_exiting))
                    }

                    override fun <TResult> onCompleteListener(task: Task<TResult>) {
                        when {
                            task.isSuccessful -> {
                                activity.startActivity(Intent(activity, P0200HomeActivity::class.java))
                                activity.finish()
                            }
                            task.isCanceled -> AlertUtil.init(activity, activity.getString(R.string.msg_something_wrong))
                        }
                    }
                }).execute()
        }

    }

    private fun isValidation(): Boolean = when {
        activity.binding.etEmail.text!!.isBlank() -> {
            activity.binding.etEmail.error = "Fill Your Email Here"
            false
        }
        !EmailValidator.isEmailValid(activity.binding.etEmail.text.toString()) -> {
            activity.binding.etEmail.error = "******@gmail.com"
            false
        }
        activity.binding.etPassword.text!!.length < 6 -> {
            activity.binding.etPassword.error = "At Least 6 Characters"
            false
        }
        activity.binding.etConPassword.text!!.length < 6 -> {
            activity.binding.etConPassword.error = "At Least 6 Characters"
            false
        }
        activity.binding.etConPassword.text.toString() != activity.etPassword.text.toString() -> {
            activity.etPassword.error = "Password mis-match"
            activity.etConPassword.error = "Password mis-match"
            false
        }
        else -> {
            activity.binding.etEmail.error = null
            activity.binding.etPassword.error = null
            activity.binding.etConPassword.error = null
            true
        }
    }
}