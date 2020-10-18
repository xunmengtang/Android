package com.reaksmeyarun.pda.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import com.reaksmeyarun.pda.view.activity.Z0200SignInActivity
import com.reaksmeyarun.pda.view.activity.Z0100SplashScreenActivity


class SplashScreenViewModel (activity : Z0100SplashScreenActivity) : ViewModel(){
    private val TAG = "SplashScreenViewModel"
    init {
        android.os.Handler().postDelayed({
            FirebaseAuth.getInstance().addAuthStateListener {
                if( FirebaseAuth.getInstance().currentUser == null){
                    activity.startActivity(Intent(activity, Z0200SignInActivity::class.java))
                }else{
                    activity.startActivity(Intent(activity, P0200HomeActivity::class.java))
                }
            }.run { activity.finish() }
        },3000)
    }
}