package com.reaksmeyarun.pda.listener

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

interface FirebaseGetChildListener {
    fun onCancelledListener(databaseError: DatabaseError)
    fun onChildMoved(dataSnapshot: DataSnapshot)
    fun onChildChanged(dataSnapshot: DataSnapshot)
    fun onChildAdded(dataSnapshot: DataSnapshot)
    fun onChildRemoved(dataSnapshot: DataSnapshot)
}