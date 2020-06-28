package com.reaksmeyarun.pda.viewmodel

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.iid.FirebaseInstanceId
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.connection.FirebaseConnection.Companion.databaseReference
import com.reaksmeyarun.pda.connection.FirebaseConnection.Companion.firebaseAuth
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.SignInDataModel
import com.reaksmeyarun.pda.datamodel.SignInDataModel.Companion.SIGN_IN_0100_CONTENT_EMAIL
import com.reaksmeyarun.pda.datamodel.SignInDataModel.Companion.SIGN_IN_0100_CONTENT_PASSWORD
import com.reaksmeyarun.pda.firebase.FirebaseEmit
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.listener.FirebaseGetListener
import com.reaksmeyarun.pda.listener.StaffSignInListener
import com.reaksmeyarun.pda.model.SignInModel
import com.reaksmeyarun.pda.model.StaffModel
import com.reaksmeyarun.pda.model.UserModel
import com.reaksmeyarun.pda.preference.UserSession
import com.reaksmeyarun.pda.utils.DataSnapShotConvertUtils.dataSnapShotToArrayList
import com.reaksmeyarun.pda.utils.EmailValidator
import com.reaksmeyarun.pda.utils.MD5Converter.md5
import com.reaksmeyarun.pda.utils.MapDataUtils
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.utils.PopupMsg.OnClickButtonYesNoCallBack
import com.reaksmeyarun.pda.utils.PopupMsg.alert
import com.reaksmeyarun.pda.view.activity.P0100SignInActivity
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import kotlinx.android.synthetic.main.p0100_sign_in_content_email.*
import kotlinx.android.synthetic.main.p0110_sign_in_content_password.*

class SignInViewModel (var signInDataModel: SignInDataModel, var activity : P0100SignInActivity) : ViewModel(){

    private val TAG = "SignInViewModel"
    private val signInRqMd = SignInModel()
    var firebaseEmit = FirebaseEmit()
//  LiveData
    var signInDM = MyMutableLiveData<SignInDataModel>()
    init {
        signInDM.setValue(signInDataModel)
        showP0100ContentEmail()
    }
    fun showP0100ContentEmail(){
        hideProgress()
        signInDataModel.showToolBar = false
        signInDataModel.state = SIGN_IN_0100_CONTENT_EMAIL
    }
    private fun showP0100ContentPassword() {
        hideProgress()
        signInDataModel.showToolBar = true
        signInDataModel.state = SIGN_IN_0100_CONTENT_PASSWORD
    }
    fun handleBackPress(view : View){
        showP0100ContentEmail()
    }
    fun handleNext(view : View){
        if(!validateFormEmail()){
            return
        }
        showProgress()
        showP0100ContentPassword()
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
                        activity.startActivity(Intent(activity, P0200HomeActivity::class.java))
                        finish()
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
                        activity.startActivity(Intent(activity, P0200HomeActivity::class.java))
                    }
                })
        }
    }
    private fun validateFormEmail() : Boolean{
        val isValidate = false
        when{
            activity.etEmail.text!!.isEmpty() -> {
                return isValidate
                activity.etEmail.error = "Fill Your Email Here"
            }!EmailValidator.isEmailValid(activity.etEmail.text.toString()) -> {
                return isValidate
                activity.etEmail.error = "******@gmail.com"
            }else -> {
                return true
                activity.etEmail.error = null
            }
        }
        return isValidate
    }
    private fun validateFormPassword() : Boolean{
        val isValidate = false
        when{activity.etPassword.text!!.length < 6 -> {
                return isValidate
                activity.etPassword.error = "At Least 6 Characters"
            }else -> {
                return true
                activity.etPassword.error = null
            }
        }
        return isValidate
    }
    fun finish(){
        activity.finish()
    }
    var isClick = 0
    fun handleSignOutFirebaseUid(view : View){
        isClick++
        if(isClick == 5) {
            if(firebaseAuth.currentUser?.uid.isNullOrEmpty())
                return
            else{
                alert(activity, activity.getString(R.string.msg_SignOut),
                    object : OnClickButtonYesNoCallBack{
                        override fun onYesCallBack() {
                            textClear()
                            firebaseAuth.signOut()
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
}