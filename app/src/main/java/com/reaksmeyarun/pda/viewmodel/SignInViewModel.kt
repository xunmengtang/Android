package com.reaksmeyarun.pda.viewmodel

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.constance.CodeConstance
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.SingInDataModel
import com.reaksmeyarun.pda.firebaseRepo.email.SendEmailVerification
import com.reaksmeyarun.pda.firebaseRepo.email.SignIn
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.preference.UserSession
import com.reaksmeyarun.pda.utils.AlertUtil
import com.reaksmeyarun.pda.utils.EmailValidator
import com.reaksmeyarun.pda.utils.PopupMsg.OnClickButtonYesNoCallBack
import com.reaksmeyarun.pda.utils.PopupMsg.alert
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import com.reaksmeyarun.pda.view.activity.Z0200SignInActivity
import com.reaksmeyarun.pda.view.activity.Z0400SignUpActivity
import kotlinx.android.synthetic.main.activity_z0200_sign_in.*

class SignInViewModel (var activity : Z0200SignInActivity) : ViewModel(){
    private val TAG = "SignInViewModel"

    fun handleSignIn(view: View) {
        if(!validateFormEmail())
            return
        SignIn(
            TAG,
            activity.etEmail.text.toString(),
            activity.etPassword.text.toString(),
            object : FireBaseListener {
                override fun onFailureListener() {
                    AlertUtil.init(activity, activity.getString(R.string.msg_toast_login_fail))
                }

                override fun <TResult> onCompleteListener(task: Task<TResult>) {
                    if (task.isSuccessful) {
                        UserSession.getInstance(activity).setUserId(FirebaseAuth.getInstance().uid ?:"")
                        val intent = Intent(activity, P0200HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                        activity.startActivity(intent)
                        activity.finish()
                    }
                }
            }
        ).execute()
    }

    private fun validateFormEmail(): Boolean = when {
        activity.etEmail.text!!.isBlank() -> {
            activity.etEmail.error = "Fill Your Email Here"
            false
        }
        !EmailValidator.isEmailValid(activity.etEmail.text.toString()) -> {
            activity.etEmail.error = "******@gmail.com"
            false
        }
        activity.etPassword.text!!.length < 6 -> {
            activity.etPassword.error = "At Least 6 Characters"
            false
        }else -> {
            activity.etEmail.error = null
            activity.etPassword.error = null
            true
        }
    }

    fun handleSignUp(view: View) {
        activity.startActivity(Intent(activity, Z0400SignUpActivity::class.java))
    }
}
