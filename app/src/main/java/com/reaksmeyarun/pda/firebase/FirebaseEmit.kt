package com.reaksmeyarun.pda.firebase

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.listener.FirebaseGetListener
import com.reaksmeyarun.pda.utils.PopupMsg

class FirebaseEmit {

    var fireBaseListener : FireBaseListener? = null
    var firebaseGetListener : FirebaseGetListener? = null

    fun get(TAG: String?="", databaseReference: DatabaseReference) {
        try{
            databaseReference?.let { databaseReference ->
                databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onCancelled(databaseError: DatabaseError) {
                        firebaseGetListener?.let { firebaseGetListener ->
                            firebaseGetListener.onCancelListener(databaseError)
                        }
                    }
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        dataSnapshot?.let { data ->
                            firebaseGetListener?.let { firebaseGetListener ->
                                firebaseGetListener.onCompleteListener(data)
                            }
                        }
                    }
                })
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }
    fun gets(TAG: String?="", databaseReference: DatabaseReference){
        try{
            databaseReference?.let { databaseReference ->
                databaseReference.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.e(TAG, "${AppConstance.DATABASE_ERROR} : $databaseError")
                    }
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        dataSnapshot?.let { data ->
                            firebaseGetListener?.let { firebaseGetListener ->
                                firebaseGetListener.onCompleteListener(data)
                            }
                        }
                    }
                })
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }

    fun getChild(TAG: String?="", databaseReference: DatabaseReference){
        try{
            databaseReference?.let { databaseReference ->
                databaseReference.addChildEventListener(object : ChildEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                        Log.e(TAG, "${AppConstance.ON_CANCEL_LISTENER} : ${AppConstance.DATABASE_ERROR} : $p0")
                    }

                    override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                        Log.d(TAG, "${AppConstance.ON_CHILD_MOVED} : $p0 | $p1")
                    }

                    override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                        Log.d(TAG, "${AppConstance.ON_CHILD_CHANGED} : $p0 | $p1")
                    }

                    override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                        Log.d(TAG, "${AppConstance.ON_CHILD_ADD} : $p0 | $p1")
                    }

                    override fun onChildRemoved(p0: DataSnapshot) {
                        Log.d(TAG, "${AppConstance.ON_CHILD_REMOVED} : $p0")
                    }
                })
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }
    fun push(context : Activity, TAG : String? = "", msg : String? = "", databaseReference: DatabaseReference, map : MutableMap<String, Any>){
        try{
            databaseReference?.let { databaseReference ->
                map?.let { mutableMap ->
                    databaseReference.updateChildren(mutableMap)
                        .addOnCanceledListener {
                            Log.e(TAG, AppConstance.ON_CANCEL_LISTENER)
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            fireBaseListener?.let { fireBaseListener ->
                                fireBaseListener.onCancelListener()
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.e(TAG, "${AppConstance.ON_FAILURE_LISTENER} : $exception")
                            PopupMsg.alert(
                                context,
                                context.getString(R.string.msg_cant_push_item)
                            )
                            fireBaseListener?.let { fireBaseListener ->
                                fireBaseListener.onFailureListener()
                            }
                        }
                        .addOnSuccessListener { void ->
                            Log.d(TAG, "${AppConstance.ON_SUCCESS_LISTENER} : $void")
                            fireBaseListener?.let { fireBaseListener ->
                                fireBaseListener.onSuccessListener()
                            }
                        }
                        .addOnCompleteListener { task ->
                            Log.d(TAG, "${AppConstance.ON_COMPLETE_LISTENER} : $task")
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            fireBaseListener?.let { fireBaseListener ->
                                fireBaseListener.onCompleteListener()
                            }
                        }
                }
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }

    fun delete(context : Activity, TAG : String? = "", msg : String? = "", databaseReference: DatabaseReference, id : String ?= ""){
        try{
            databaseReference?.let { databaseReference ->
                id?.let { id ->
                    databaseReference.child(id).removeValue()
                        .addOnCanceledListener {
                            Log.e(TAG, AppConstance.ON_CANCEL_LISTENER)
                            fireBaseListener?.let { fireBaseListener ->
                                fireBaseListener.onCancelListener()
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.e(TAG, "${AppConstance.ON_FAILURE_LISTENER} : $exception")
                            PopupMsg.alert(
                                context,
                                context.getString(R.string.msg_cant_delete_item)
                            )
                            fireBaseListener?.let { fireBaseListener ->
                                fireBaseListener.onFailureListener()
                            }
                        }
                        .addOnSuccessListener { void ->
                            Log.d(TAG, "${AppConstance.ON_SUCCESS_LISTENER} : $void")
                            fireBaseListener?.let { fireBaseListener ->
                                fireBaseListener.onSuccessListener()
                            }
                        }
                        .addOnCompleteListener { task ->
                            Log.d(TAG, "${AppConstance.ON_COMPLETE_LISTENER} : $task")
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            fireBaseListener?.let { fireBaseListener ->
                                fireBaseListener.onCompleteListener()
                            }
                        }
                }
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }

    fun edit(context : Activity, TAG : String? = "", msg : String? = "", databaseReference: DatabaseReference, id : String?= "", node : String?= "", model : Any){
        try{
            databaseReference?.let { databaseReference ->
                databaseReference.child(id!!).child(node!!).setValue(model)
                    .addOnCanceledListener {
                        Log.e(TAG, AppConstance.ON_CANCEL_LISTENER)
                        fireBaseListener?.let { fireBaseListener ->
                            fireBaseListener.onCancelListener()
                        }
                    }
                    .addOnFailureListener {
                        Log.e(TAG, "${AppConstance.ON_FAILURE_LISTENER} : $it")
                        PopupMsg.alert(
                            context,
                            context.getString(R.string.msg_cant_edit_item)
                        )
                        fireBaseListener?.let { fireBaseListener ->
                            fireBaseListener.onFailureListener()
                        }
                    }
                    .addOnSuccessListener {
                        Log.d(TAG, "${AppConstance.ON_SUCCESS_LISTENER} : $it")
                        fireBaseListener?.let { fireBaseListener ->
                            fireBaseListener.onFailureListener()
                        }
                    }
                    .addOnCompleteListener {
                        Log.d(TAG, "${AppConstance.ON_COMPLETE_LISTENER} : $it")
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        fireBaseListener?.let { fireBaseListener ->
                            fireBaseListener.onCompleteListener()
                        }
                    }
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }
}