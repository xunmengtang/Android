package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.reaksmeyarun.pda.constance.CodeConstance
import com.reaksmeyarun.pda.utils.EmailValidator
import com.reaksmeyarun.pda.view.activity.Z0200SignInActivity
import com.reaksmeyarun.pda.view.activity.Z0400SignUpActivity
import kotlinx.android.synthetic.main.activity_z0200_sign_in.*

class SignInViewModel (var activity : Z0200SignInActivity) : ViewModel(){
    private val TAG = "SignInViewModel"
    private var verificationId = ""
    private var token: PhoneAuthProvider.ForceResendingToken?= null
    init {

    }
//    fun handleSignIn(view: View){
//        if(!validateFormPassword()) return
//        showProgress()
//        SignIn(
//            TAG,
//            activity.etEmail.text.toString(),
//            activity.etPassword.text.toString(),
//            object : FireBaseListener {
//                override fun onFailureListener() {
//                    alert(activity, activity.getString(R.string.msg_toast_login_fail))
//                }
//
//                override fun <TResult> onCompleteListener(task: Task<TResult>) {
//                    if (task.isSuccessful) {
//                        if (!isEmailVerify()) {
//                            alert(activity, activity.getString(R.string.msg_to_send_verity_email),
//                                object : OnClickButtonYesNoCallBack {
//                                    override fun onYesCallBack() {
//                                        SendEmailVerification(
//                                            TAG,
//                                            object : FireBaseListener {
//                                                override fun onFailureListener() {
//                                                    alert(
//                                                        activity,
//                                                        activity.getString(R.string.msg_something_wrong)
//                                                    )
//                                                }
//
//                                                override fun <TResult> onCompleteListener(task: Task<TResult>) {
//                                                    when {
//                                                        task.isSuccessful -> alert(activity,
//                                                            activity.getString(R.string.msg_send_verity_email),
//                                                            object :
//                                                                PopupMsg.OnClickButtonCloseCallBack {
//                                                                override fun onCloseCallBack() {
//                                                                    InstanceAuth.signOut().let {
//                                                                        textClear()
//                                                                        showP0100ContentEmail()
//                                                                    }
//                                                                }
//                                                            })
//                                                        task.isCanceled -> alert(
//                                                            activity,
//                                                            activity.getString(R.string.msg_cancel)
//                                                        )
//                                                    }
//                                                }
//                                            }).execute()
//                                    }
//
//                                    override fun onNoCallBack() {    /* Do nothing*/
//                                    }
//                                })
//                        } else {
//                            activity.startActivity(activity, P0200HomeActivity::class.java)
//                        }
//                    }
//                }
//            }).execute()
//        hideProgress()
//    }
    private fun validateFormEmail() : Boolean = when {
            activity.etPhoneNumber.text!!.isBlank() -> {
                activity.etPhoneNumber.error = "Fill Your Email Here"
                false
            }!EmailValidator.isEmailValid(activity.etPhoneNumber.text.toString()) -> {
                activity.etPhoneNumber.error = "******@gmail.com"
                false
            }else -> {
                activity.etPhoneNumber.error = null
                true
            }
        }
//    private fun validateFormPassword() : Boolean = when {
//            activity.etPassword.text!!.length < 6 -> {
//                activity.etPassword.error = "At Least 6 Characters"
//                false
//            }else -> {
//                activity.etPassword.error = null
//                true
//            }
//        }
//    private fun textClear(){
//        activity.etEmail.text?.clear()
//        activity.etPassword.text?.clear()
//    }
    private fun isEmailVerify() : Boolean = when{
        FirebaseAuth.getInstance().currentUser!!.isEmailVerified -> true
        else -> false
    }
    fun handleSignUp(view : View){
        activity.startActivityForResult(activity, Z0400SignUpActivity::class.java, CodeConstance.SIGN_UP_REQUEST_CODE)
    }
}