package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.reaksmeyarun.pda.view.activity.D0100DashboardActivity

class DashboardViewModel (var activity : D0100DashboardActivity): ViewModel() {
    private val TAG = "DashboardViewModel"

    fun handleBackPress(view : View){
        activity.finish()
    }
}