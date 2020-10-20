package com.reaksmeyarun.pda.firebaseRepo.fav

import android.app.Activity
import com.google.firebase.database.DatabaseReference
import com.reaksmeyarun.pda.base.BaseFirebase
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.firebaseRepo.RequestItem
import com.reaksmeyarun.pda.listener.FirebaseGetListener
import com.reaksmeyarun.pda.preference.UserSession

class RequestFav(var activity : Activity) : BaseFirebase() {
    private val TAG = "RequestCart"
    private var firebaseGetListener : FirebaseGetListener?= null
    fun onSingleValueListener(listener: FirebaseGetListener){
        this.firebaseGetListener = listener
    }
    fun execute(){
        firebaseGetListener?.let {
            get(TAG, cartQueryRequest(), it)
        }
    }

    private fun cartQueryRequest() : DatabaseReference {
        return databaseReference(FirebaseConstance.CART).child(UserSession.getInstance(activity).getUserId())
    }

    class ResponseCart(
        var id : String?= "",
        var item : RequestItem.ResponseItem ?= null,
        var quanities : Int ?= 0,
        var timestamp : String ?= ""
    )
}