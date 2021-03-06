package com.reaksmeyarun.pda.listener

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

interface FirebaseGetListener {
    fun onDataChange(dataSnapshot: DataSnapshot)
    fun onCancelListener(databaseError: DatabaseError)
}