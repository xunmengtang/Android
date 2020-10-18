package com.reaksmeyarun.pda.firebaseRepo

import android.app.Activity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.CollectionReference
import com.reaksmeyarun.pda.base.BaseFirebase
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.listener.FirebaseGetChildListener

class RequestItem(var activity : Activity) : BaseFirebase(){
    private val TAG = "RequestItem"
    private var firebaseGetChildListener : FirebaseGetChildListener ?= null
    fun onChildListener(listener: FirebaseGetChildListener){
        firebaseGetChildListener = listener
    }
    fun execute(){
        getChild(activity, TAG, itemRequest(),
            object : FirebaseGetChildListener{
                override fun onCancelledListener(databaseError: DatabaseError) { firebaseGetChildListener?.onCancelledListener(databaseError) }
                override fun onChildMoved(dataSnapshot: DataSnapshot) { firebaseGetChildListener?.onChildMoved(dataSnapshot) }
                override fun onChildChanged(dataSnapshot: DataSnapshot) {  firebaseGetChildListener?.onChildChanged(dataSnapshot) }
                override fun onChildAdded(dataSnapshot: DataSnapshot) {  firebaseGetChildListener?.onChildAdded(dataSnapshot) }
                override fun onChildRemoved(dataSnapshot: DataSnapshot) {  firebaseGetChildListener?.onChildRemoved(dataSnapshot) }
            }
        )
    }

    private fun itemRequest() : DatabaseReference {
        return databaseReference(FirebaseConstance.ITEM_NODE)
    }

    class ResponseItem(
        var categoryID : String?= "",
        var cost : Double?= 0.0,
        var createBy : String ?= "",
        var id : String ?= "",
        var desc : String?= "",
        var itemCode : String?= "",
        var itemName : String?= "",
        var price : Double?= 0.0,
        var image : RequestCategory.Image ?= null
    )
}