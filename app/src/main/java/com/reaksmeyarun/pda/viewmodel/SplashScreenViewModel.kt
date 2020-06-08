package com.reaksmeyarun.pda.viewmodel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.view.activity.P0100SignInActivity
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import com.reaksmeyarun.pda.view.activity.Z0100SplashScreenActivity


class SplashScreenViewModel (activity : Z0100SplashScreenActivity) : ViewModel(){
    private val TAG = "SplashScreenViewModel"
    init {
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (ex: Exception) {
                    Log.e(TAG, "${AppConstance.EXCEPTION}: $ex")
                } catch (ex : ExceptionInInitializerError){
                    Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
                } finally {
                    FirebaseConnection.firebaseAuth.addAuthStateListener {
                        if(FirebaseConnection.firebaseAuth.currentUser == null){
                            activity.startActivity(Intent(activity, P0100SignInActivity::class.java))
                        }else{
                            activity.startActivity(Intent(activity, P0200HomeActivity::class.java))
                        }
                    activity.finish()
                    }
                }
            }
        }
        thread.start()
    }
}