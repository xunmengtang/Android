package com.reaksmeyarun.pda.firebase

import android.app.Activity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.reaksmeyarun.pda.base.BaseFirebaseEvenListener
import com.reaksmeyarun.pda.constance.FirebaseQueryConstance
import com.reaksmeyarun.pda.listener.FirebaseGetChildListener

class RequestItem(var activity : Activity) : BaseFirebaseEvenListener(){
    private val TAG = "RequestItem"
    private var firebaseGetChildListener : FirebaseGetChildListener ?= null
    fun onChildListener(listener: FirebaseGetChildListener){
        firebaseGetChildListener = listener
    }
    fun execute(){
        getChild(activity, TAG, FirebaseQueryConstance.ITEM_QUERY,
            object : FirebaseGetChildListener{
                override fun onCancelledListener(databaseError: DatabaseError) { firebaseGetChildListener?.onCancelledListener(databaseError) }
                override fun onChildMoved(dataSnapshot: DataSnapshot) { firebaseGetChildListener?.onChildMoved(dataSnapshot) }
                override fun onChildChanged(dataSnapshot: DataSnapshot) {  firebaseGetChildListener?.onChildChanged(dataSnapshot) }
                override fun onChildAdded(dataSnapshot: DataSnapshot) {  firebaseGetChildListener?.onChildAdded(dataSnapshot) }
                override fun onChildRemoved(dataSnapshot: DataSnapshot) {  firebaseGetChildListener?.onChildRemoved(dataSnapshot) }
            }
        )
    }
}