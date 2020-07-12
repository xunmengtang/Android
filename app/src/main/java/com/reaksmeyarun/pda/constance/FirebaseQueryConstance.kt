package com.reaksmeyarun.pda.constance

import com.reaksmeyarun.pda.connection.FirebaseConnection.Companion.InstanceDatabaseReference
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.CATEGORY_NODE
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ITEM_INFORMATION_NODE
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ITEM_NODE

class FirebaseQueryConstance {
    companion object{
        val CATEGORY_QUERY = InstanceDatabaseReference.child(CATEGORY_NODE)
        val ITEM_OF_CATEGORY_QUERY = InstanceDatabaseReference.child(CATEGORY_NODE).child(ITEM_INFORMATION_NODE)
        val ITEM_QUERY = InstanceDatabaseReference.child(ITEM_NODE)
    }
}