package com.reaksmeyarun.pda.firebaseRepo

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.reaksmeyarun.pda.base.BaseFirebase
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.listener.FireBaseListener

class RequestClearAllCarts(var activity : Activity) : BaseFirebase() {
    private val TAG = "RequestClearAllCarts"
    private var fireBaseListener : FireBaseListener ?= null
    lateinit var rqClearCartModel : RequestClearCartModel

    fun listener(listener: FireBaseListener){
        this.fireBaseListener = listener
    }

    fun execute(){
        fireBaseListener?.let {
            delete(activity, TAG, deleteCartQueryRequest(rqClearCartModel.orderId ?: ""), it)
        }
    }

    private fun deleteCartQueryRequest(orderID : String) : DatabaseReference{
        return databaseReference(FirebaseConstance.CART).child(orderID)
    }

    class RequestClearCartModel(var orderId : String?= "")

}