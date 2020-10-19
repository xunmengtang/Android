package com.reaksmeyarun.pda.utils

import android.app.Activity
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.LinearLayout


object ScreenSize {
    fun getWidthPixels(activity : Activity) : Int{
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }
    fun getLayoutWidthPixels(view : View, callback: OnReadyCallback){
        view.viewTreeObserver.addOnGlobalLayoutListener {
            callback.onReady(view.measuredHeight)
        }
    }
    interface OnReadyCallback{
        fun onReady(value: Int)
    }

}