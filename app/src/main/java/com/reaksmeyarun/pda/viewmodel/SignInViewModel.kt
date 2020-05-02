package com.reaksmeyarun.pda.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.reaksmeyarun.pda.model.SignInModel
import com.reaksmeyarun.pda.view.activity.P0100SignInActivity
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import java.util.logging.Handler

class SignInViewModel(var activity : P0100SignInActivity) : ViewModel(){
    val TAG = "SignInViewModel"
    private val signInRqMd =
        SignInModel()
    init {
        signInRqMd.email = activity.binding.etEmail.text.toString()
        signInRqMd.password = activity.binding.etPass.text.toString()
    }

    fun handleSignInClick(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        activity.startActivity(Intent(activity, P0200HomeActivity::class.java))
        activity.finish()
    }
}