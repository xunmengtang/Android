package com.reaksmeyarun.pda.viewmodel

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.connection.FirebaseConnection.Companion.databaseReference
import com.reaksmeyarun.pda.connection.FirebaseConnection.Companion.firebaseAuth
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.SignInDataModel
import com.reaksmeyarun.pda.datamodel.SignInDataModel.Companion.SIGN_IN_0100_CONTENT_EMAIL
import com.reaksmeyarun.pda.datamodel.SignInDataModel.Companion.SIGN_IN_0100_CONTENT_PASSWORD
import com.reaksmeyarun.pda.firebase.FirebaseEmit
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.listener.StaffSignInListener
import com.reaksmeyarun.pda.utils.EmailValidator
import com.reaksmeyarun.pda.utils.PopupMsg.OnClickButtonYesNoCallBack
import com.reaksmeyarun.pda.utils.PopupMsg.alert
import com.reaksmeyarun.pda.view.activity.P0100SignInActivity
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import kotlinx.android.synthetic.main.p0100_sign_in_content_email.*
import kotlinx.android.synthetic.main.p0110_sign_in_content_password.*

class SignInViewModel (var signInDataModel: SignInDataModel, var activity : P0100SignInActivity) : ViewModel(){
    private val TAG = "SignInViewModel"
    private var msg_EmailValidate = "@gmail.com"
    private val firebaseEmit = FirebaseEmit()
    var signInDM = MyMutableLiveData<SignInDataModel>()
    init {
        signInDM.setValue(signInDataModel)
        showP0100ContentEmail()
    }
    fun showP0100ContentEmail(){
        Log.d(TAG, "IsNullOrEmpty : ${firebaseAuth.currentUser?.uid.isNullOrEmpty()}")
        msg_EmailValidate = if(firebaseAuth.currentUser?.uid.isNullOrEmpty())
            "@gmail.com"
        else
            "@pos.io"
        signInDataModel.showResetPassword = firebaseAuth.currentUser?.uid.isNullOrEmpty()
        signInDataModel.showToolBar = false
        signInDataModel.state = SIGN_IN_0100_CONTENT_EMAIL
        hideProgress()
    }
    private fun showP0100ContentPassword() {
        signInDataModel.showToolBar = true
        signInDataModel.state = SIGN_IN_0100_CONTENT_PASSWORD
        hideProgress()
    }
    fun handleBackPress(view : View){
        showP0100ContentEmail()
    }
    fun handleNext(view : View){
        if(!validateFormEmail())
            return
        showProgress()
        showP0100ContentPassword()
    }
    fun handleResetPassword(view : View){
        alert(activity, activity.getString(R.string.msg_to_send_reset_pass_email),
        object : OnClickButtonYesNoCallBack{
            override fun onYesCallBack() {
                firebaseEmit.resetPassword(TAG, firebaseAuth, activity.etEmail.text.toString(),
                    object : FireBaseListener{
                        override fun onFailureListener() {
                            alert(activity, activity.getString(R.string.msg_something_wrong))
                        }
                        override fun <TResult> onCompleteListener(task: Task<TResult>) {
                            if (task.isSuccessful)
                                alert(activity, activity.getString(R.string.msg_send_reset_pass_email))
                            else if(task.isCanceled)
                                alert(activity, activity.getString(R.string.msg_cancel))
                        }
                    })
            }
            override fun onNoCallBack() {/* DO nothing*/}
        })
    }
    private fun showProgress(){
        signInDataModel.showProgress = true
    }
    private fun hideProgress(){
        android.os.Handler().postDelayed({
            signInDataModel.showProgress = false
        },500)
    }
    fun handleSignIn(view: View){
        if(!validateFormPassword())
            return
        showProgress()
        if(firebaseAuth.currentUser?.uid.isNullOrEmpty()){
            firebaseEmit.signIn(TAG, activity.etEmail.text.toString(), activity.etPassword.text.toString(), firebaseAuth,
                object : FireBaseListener{
                override fun onFailureListener() {
                    alert(activity, activity.getString(R.string.msg_toast_login_fail))
                    hideProgress()
                }
                override fun <TResult> onCompleteListener(task: Task<TResult>) {
                    if(task.isSuccessful){
                        if(!isEmailVerify()) {
                            alert(activity, activity.getString(R.string.msg_to_send_verity_email),
                            object : OnClickButtonYesNoCallBack{
                                override fun onYesCallBack() {
                                    firebaseEmit.sendEmailVerification(TAG, firebaseAuth,
                                        object : FireBaseListener{
                                            override fun onFailureListener() {
                                                alert(activity, activity.getString(R.string.msg_something_wrong))
                                            }
                                            override fun <TResult> onCompleteListener(task: Task<TResult>) {
                                                if(task.isSuccessful)
                                                    alert(activity, activity.getString(R.string.msg_send_verity_email))
                                                else if (task.isCanceled)
                                                    alert(activity, activity.getString(R.string.msg_cancel))
                                            }
                                        })
                                }
                                override fun onNoCallBack() {    /* Do nothing*/   }
                            })
                            return
                        }else{
                            startActivity(activity, P0200HomeActivity::class.java)
                        }
                    }
                }
            })
        }else{
            firebaseEmit.staffSignIn(TAG, activity.etEmail.text.toString(), activity.etPassword.text.toString(),
                databaseReference(firebaseAuth.uid).child(AppConstance.OWNER_NODE).child(AppConstance.STAFF_NODE), object : StaffSignInListener{
                    override fun onFailure() {
                        hideProgress()
                        alert(activity, activity.getString(R.string.msg_toast_login_fail))
                    }

                    override fun onSuccess() {
                        startActivity(activity, P0200HomeActivity::class.java)
                    }
                })
        }
    }
    private fun validateFormEmail() : Boolean{
        val isValidate = false
        return when{
            activity.etEmail.text!!.isBlank() -> {
                activity.etEmail.error = "Fill Your Email Here"
                isValidate
            }!EmailValidator.isEmailValid(activity.etEmail.text.toString()) -> {
                activity.etEmail.error = "******@gmail.com"
                isValidate
            }else -> {
                activity.etEmail.error = null
                true
            }
        }
    }
    private fun validateFormPassword() : Boolean{
        val isValidate = false
        return when{
            activity.etPassword.text!!.length < 6 -> {
                activity.etPassword.error = "At Least 6 Characters"
                isValidate
            }else -> {
                activity.etPassword.error = null
                true
            }
        }
    }
    var isClick = 0
    fun handleSignOutFirebaseUid(view : View){
        isClick++
        if(isClick == 20) {
            if(firebaseAuth.currentUser?.uid.isNullOrEmpty())
                return
            else{
                alert(activity, activity.getString(R.string.msg_SignOut),
                    object : OnClickButtonYesNoCallBack{
                        override fun onYesCallBack() {
                            firebaseAuth.signOut()
                            textClear()
                            showP0100ContentEmail()
                        }
                        override fun onNoCallBack() {}
                    }
                )
            }
            isClick = 0
        }
    }
    private fun textClear(){
        activity.etEmail.text?.clear()
        activity.etPassword.text?.clear()
    }
    private fun isEmailVerify() : Boolean{
        val isEmailVerify = false
        return when{
            firebaseAuth.currentUser!!.isEmailVerified ->
                true
            else ->
                isEmailVerify
        }
    }
    private fun <T> startActivity(activity : P0100SignInActivity, classModel : Class<T>){
        activity.startActivity(Intent(activity.applicationContext, classModel))
        activity.finish()
    }
}