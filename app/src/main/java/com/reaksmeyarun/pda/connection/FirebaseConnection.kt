package com.reaksmeyarun.pda.connection

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.reaksmeyarun.pda.constance.AppConstance

class FirebaseConnection {
    companion object {
        val firebaseAuth = FirebaseAuth.getInstance()
        private val firebaseDatabase = FirebaseDatabase.getInstance()

        fun databaseReference(node : String? = "") : DatabaseReference{
            return firebaseDatabase.getReference(node.toString())
        }
    }
}