package com.reaksmeyarun.pda.viewmodel

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
                    AlertUtil.init(activity, R.layout.alert, activity.getString(R.string.msg_toast_login_fail))
                }

                override fun <TResult> onCompleteListener(task: Task<TResult>) {
                    if (task.isSuccessful) {
                        if (!isEmailVerify()) {
                            alert(activity,
                                activity.getString(R.string.msg_to_send_verity_email),
                                object : OnClickButtonYesNoCallBack {
                                    override fun onYesCallBack() {
                                        SendEmailVerification(
                                            TAG,
                                            object : FireBaseListener {
                                                override fun onFailureListener() {
                                                    AlertUtil.init(activity, R.layout.alert, activity.getString(R.string.msg_something_wrong))
                                                }

                                                override fun <TResult> onCompleteListener(task: Task<TResult>) {
                                                    Log.d("TAG", "this is result task :$task")
                                                    when {
                                                        task.isSuccessful ->{
                                                            AlertUtil.init(activity, R.layout.alert, activity.getString(R.string.msg_send_verity_email))
                                                            FirebaseAuth.getInstance().signOut()
                                                        }
                                                        task.isCanceled -> alert(
                                                            activity,
                                                            activity.getString(R.string.msg_cancel)
                                                        )
                                                    }
                                                }
                                            }).execute()
                                    }

                                    override fun onNoCallBack() {    /* Do nothing*/
                                    }
                                })
                        } else {
                            UserSession.getInstance(activity).setUserId(FirebaseAuth.getInstance().uid ?:"")
                            activity.startActivity(activity, P0200HomeActivity::class.java)
                            activity.finish()
                        }
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

    fun textClear(){
        activity.etEmail.text?.clear()
        activity.etPassword.text?.clear()
    }

    private fun isEmailVerify(): Boolean = when {
        FirebaseAuth.getInstance().currentUser!!.isEmailVerified -> true
        else -> false
    }

    fun handleSignUp(view: View) {
        activity.startActivityForResult(
            activity,
            Z0400SignUpActivity::class.java,
            CodeConstance.SIGN_UP_REQUEST_CODE
        )
    }
}
