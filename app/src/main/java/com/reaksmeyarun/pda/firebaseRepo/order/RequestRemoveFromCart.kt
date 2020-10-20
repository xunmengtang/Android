package com.reaksmeyarun.pda.firebaseRepo.order

import android.app.Activity
import com.google.firebase.database.DatabaseReference
import com.reaksmeyarun.pda.base.BaseFirebase
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.preference.UserSession

class RequestRemoveFromCart(var activity : Activity) : BaseFirebase() {
    private val TAG = "RequestRemoveFromCart"
    private var fireBaseListener : FireBaseListener ?= null
    lateinit var model : RequestRemoveFromCartModel

    fun listener(listener: FireBaseListener){
        this.fireBaseListener = listener
    }

    fun execute(){
        fireBaseListener?.let {
            delete(activity, TAG, deleteCartQueryRequest(), it)
        }
    }

    private fun deleteCartQueryRequest() : DatabaseReference{
        return databaseReference(FirebaseConstance.CART).child(UserSession.getInstance(activity).getUserId()).child(model.itemID!!)
    }

    class RequestRemoveFromCartModel(var itemID : String ?= "")

}