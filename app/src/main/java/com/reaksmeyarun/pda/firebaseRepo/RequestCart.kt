package com.reaksmeyarun.pda.firebaseRepo

import android.app.Activity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.reaksmeyarun.pda.base.BaseFirebase
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.listener.FirebaseGetListener

class RequestCart(var activity : Activity) : BaseFirebase() {
    private val TAG = "RequestCart"
    private var firebaseGetListener : FirebaseGetListener ?= null
    fun onSingleValueListener(listener: FirebaseGetListener){
        this.firebaseGetListener = listener
    }
    fun execute(){
        firebaseGetListener?.let {
            get(TAG, cartQueryRequest(), it)
        }
    }

    private fun cartQueryRequest() : DatabaseReference{
        return databaseReference(FirebaseConstance.CART)
    }

    class ResponseCart(
        var item : RequestItem.ResponseItem ?= null,
        var quanities : Int ?= 0,
        var timestamp : String ?= "",
        var subTotal : Float ?= 0f
    )
}