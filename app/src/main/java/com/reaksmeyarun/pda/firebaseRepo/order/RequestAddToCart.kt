package com.reaksmeyarun.pda.firebaseRepo.order

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.reaksmeyarun.pda.base.BaseFirebase
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.firebaseRepo.RequestCart
import com.reaksmeyarun.pda.listener.FireBaseListener

class RequestAddToCart(var activity : Activity) : BaseFirebase() {
    private val TAG = "RequestAddToCart"
    private var fireBaseListener : FireBaseListener ?= null
    lateinit var model : RequestClearCartModel

    fun listener(listener: FireBaseListener){
        this.fireBaseListener = listener
    }

    fun execute(){
        fireBaseListener?.let {
            push(activity, TAG, addToCartQueryRequest(), model.mutable!!, it)
        }
    }

    private fun addToCartQueryRequest() : DatabaseReference{
        return databaseReference(FirebaseConstance.CART).child(model.orderId!!)
    }

    fun key() : String {
        return addToCartQueryRequest().push().key ?: "N/A"
    }

    class RequestClearCartModel(
        var orderId : String?= "",
        var mutable : MutableMap<String, RequestCart.ResponseCart>?= null
    )

}