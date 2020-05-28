package com.reaksmeyarun.pda.viewmodel

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.model.SignInModel
import com.reaksmeyarun.pda.view.activity.P0100SignInActivity
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import kotlinx.android.synthetic.main.activity_p0100_sign_in.*


class SignInViewModel(var activity : P0100SignInActivity) : ViewModel(){
    private val TAG = "SignInViewModel"
    private val signInRqMd = SignInModel()

    fun handleSignInClick(view : View){
        try{
            if(!validateForm())
                return
            activity.binding.progressing.visibility = View.VISIBLE
            signInRqMd.email = activity.binding.etEmail.text.toString().trim()
            signInRqMd.password = activity.binding.etPass.text.toString().trim()
            if(signInRqMd.email.isNotEmpty() && signInRqMd.password.isNotEmpty()){
                Log.d(TAG, "${activity.getString(R.string.email)} : ${signInRqMd.email} & ${activity.getString(R.string.password)} : ${signInRqMd.password}")
                FirebaseConnection.firebaseAuth.signInWithEmailAndPassword(
                    signInRqMd.email,
                    signInRqMd.password)
                    .addOnSuccessListener {
                        Log.e(TAG, "${AppConstance.ON_SUCCESS_LISTENER} : $it")
                    }
                    .addOnCompleteListener{
                        Log.d(TAG, "${AppConstance.ON_COMPLETE_LISTENER} : $it")
                        activity.startActivity(Intent(activity, P0200HomeActivity::class.java))
                        activity.finish()
//                        var user = FirebaseConnection.firebaseAuth.currentUser
//                        if (user != null) {
//                            if(user.isEmailVerified){
//                                activity.startActivity(Intent(activity, P0200HomeActivity::class.java))
//                                activity.finish()
//                            } else{
//                                user.sendEmailVerification()
//                                    .addOnCompleteListener { task ->
//                                        if (task.isSuccessful){
//                                            Log.d(TAG, "${AppConstance.ON_COMPLETE_LISTENER} : $it")
//                                        }else{
//                                            PopupMsg.alert(activity, activity.getString(R.string.msg_verity_email))
//                                        }
//                                    }
//                            }
//                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(activity, activity.getString(R.string.msg_toast_login_fail), Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "${AppConstance.ON_FAILURE_LISTENER} : $it")
                    }
                    .addOnCanceledListener {
                        Toast.makeText(activity, activity.getString(R.string.msg_access_deny), Toast.LENGTH_SHORT).show()
                    }
            }else{
                Toast.makeText(activity, activity.getString(R.string.msg_toast_check_email_and_pass), Toast.LENGTH_SHORT).show()
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }finally {
            activity.binding.progressing.visibility = View.GONE
        }
    }

    private fun validateForm(): Boolean {
        var valid = true
        if (TextUtils.isEmpty(activity.etEmail.text.toString())) {
            activity.etEmail.setError("!")
            valid = false
        } else if (TextUtils.isEmpty(activity.etEmail.text.toString())) {
            activity.etEmail.setError("!")
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(activity.etEmail.text.toString()).matches()) {
            activity.etEmail.setError("@gmail.com")
            valid = false
        } else if (activity.etPass.text.toString().length < 6) {
            activity.etPass.setError("At least 6 characters")
            valid = false
        } else {
            activity.etPass.setError(null)
            activity.etEmail.setError(null)
        }
        return valid
    }
}