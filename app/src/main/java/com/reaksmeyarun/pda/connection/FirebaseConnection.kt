package com.reaksmeyarun.pda.connection

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseConnection {
    companion object {
        private val firebaseDatabase = FirebaseDatabase.getInstance()

        fun databaseReference(node : String? = "") : DatabaseReference{
            return firebaseDatabase.getReference(node!!)
        }
        val InstancePhoneAuth = PhoneAuthProvider.getInstance()
        val InstanceAuth = FirebaseAuth.getInstance()
        val InstanceDatabaseReference = firebaseDatabase.reference
    }
}