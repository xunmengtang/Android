package com.reaksmeyarun.pda.firebase

import android.app.Activity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.reaksmeyarun.pda.base.BaseFirebase
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.listener.FirebaseGetChildListener

class RequestCategory(var activity : Activity) : BaseFirebase() {
    private val TAG = "RequestCategory"
    private var firebaseGetChildListener : FirebaseGetChildListener ?= null
    fun onChildListener(listener: FirebaseGetChildListener){
        this.firebaseGetChildListener = listener
    }
    fun execute(){
        getChild(activity, TAG, categoryQueryRequest(),
            object : FirebaseGetChildListener{
                override fun onCancelledListener(databaseError: DatabaseError) { firebaseGetChildListener?.onCancelledListener(databaseError) }
                override fun onChildMoved(dataSnapshot: DataSnapshot) { firebaseGetChildListener?.onChildMoved(dataSnapshot) }
                override fun onChildChanged(dataSnapshot: DataSnapshot) {  firebaseGetChildListener?.onChildChanged(dataSnapshot) }
                override fun onChildAdded(dataSnapshot: DataSnapshot) {  firebaseGetChildListener?.onChildAdded(dataSnapshot) }
                override fun onChildRemoved(dataSnapshot: DataSnapshot) {  firebaseGetChildListener?.onChildRemoved(dataSnapshot) }
            }
        )
    }

    private fun categoryQueryRequest() : DatabaseReference{
        return databaseReference(FirebaseConstance.CATEGORY_NODE)
    }
}