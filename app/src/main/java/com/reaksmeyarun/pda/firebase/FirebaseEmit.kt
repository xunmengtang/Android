package com.reaksmeyarun.pda.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.database.*
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.listener.FirebaseGetChildListener
import com.reaksmeyarun.pda.listener.FirebaseGetListener
import com.reaksmeyarun.pda.utils.PopupMsg

@Suppress("NAME_SHADOWING")
class FirebaseEmit {

//    var fireBaseListener : FireBaseListener? = null
//    var firebaseEditListener : FireBaseListener ?= null
//    var firebaseDeleteListener : FireBaseListener ?= null
//    var firebaseGetListener : FirebaseGetListener? = null
//    var firebaseGetChildListener : FirebaseGetChildListener ?= null

    fun get(
        TAG: String?="",
        databaseReference: DatabaseReference,
        listener: FirebaseGetListener
    ) {
        try{
            databaseReference.let { databaseReference ->
                databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.d(TAG, "${AppConstance.DATABASE_ERROR} : $databaseError")
                        listener.onCancelListener(databaseError)
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        Log.i(TAG, "${AppConstance.ON_DATA_CHANGED} : $dataSnapshot")
                        dataSnapshot.let { data ->
                            listener.onDataChange(data)
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

    fun gets(
        TAG: String?="",
        databaseReference: DatabaseReference,
        listener: FirebaseGetListener
    ){
        try{
            databaseReference.let { databaseReference ->
                databaseReference.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.e(TAG, "${AppConstance.DATABASE_ERROR} : $databaseError")
                        listener.onCancelListener(databaseError)
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        dataSnapshot.let { data ->
                            listener.onDataChange(data)
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

    fun getChild(
        activity : Activity,
        TAG : String?="",
        databaseReference : DatabaseReference,
        listener : FirebaseGetChildListener
    ){
        try{
            databaseReference.addChildEventListener(object : ChildEventListener{
                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(TAG, "${AppConstance.ON_CANCEL_LISTENER} : ${AppConstance.DATABASE_ERROR} : $databaseError")
                    PopupMsg.alert(activity, activity.getString(R.string.msg_something_wrong))
                    listener.onCancelledListener(databaseError)
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot, key: String?) {
                    Log.d(TAG, "${AppConstance.ON_CHILD_MOVED} : $key | $dataSnapshot")
                    listener.onChildMoved(dataSnapshot)
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot, key: String?) {
                    Log.d(TAG, "${AppConstance.ON_CHILD_CHANGED} : $key | $dataSnapshot")
                    listener.onChildChanged(dataSnapshot)
                }

                override fun onChildAdded(dataSnapshot: DataSnapshot, key: String?) {
                    Log.d(TAG, "${AppConstance.ON_CHILD_ADD} : $dataSnapshot")
                    listener.onChildAdded(dataSnapshot)
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                    Log.d(TAG, "${AppConstance.ON_CHILD_REMOVED} : $dataSnapshot")
                    listener.onChildRemoved(dataSnapshot)
                }
            })
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }

    fun push(
        context : Activity,
        TAG : String? = "",
        databaseReference : DatabaseReference,
        map : MutableMap<String, Any>,
        listener : FireBaseListener
    ){
        try{
            databaseReference.let { databaseReference ->
                map.let { mutableMap ->
                    databaseReference.updateChildren(mutableMap)
                        .addOnCanceledListener {
                            Log.e(TAG, AppConstance.ON_CANCEL_LISTENER)
                            listener.onCancelListener()
                        }
                        .addOnFailureListener { exception ->
                            Log.e(TAG, "${AppConstance.ON_FAILURE_LISTENER} : $exception")
                            PopupMsg.alert(
                                context,
                                context.getString(R.string.msg_cant_push_item)
                            )
                            listener.onFailureListener()
                        }
                        .addOnSuccessListener { void ->
                            Log.d(TAG, "${AppConstance.ON_SUCCESS_LISTENER} : $void")
                            listener.onSuccessListener()
                        }
                        .addOnCompleteListener { task ->
                            Log.d(TAG, "${AppConstance.ON_COMPLETE_LISTENER} : $task")
                            listener.onCompleteListener()
                        }
                }
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }

    fun delete(
        context : Activity,
        TAG : String? = "",
        databaseReference : DatabaseReference,
        listener : FireBaseListener){
        try{
            databaseReference.let { databaseReference ->
                databaseReference.removeValue()
                    .addOnCanceledListener {
                        Log.e(TAG, AppConstance.ON_CANCEL_LISTENER)
                        listener.onCancelListener()
                    }
                    .addOnFailureListener { exception ->
                        Log.e(TAG, "${AppConstance.ON_FAILURE_LISTENER} : $exception")
                        PopupMsg.alert(
                            context,
                            context.getString(R.string.msg_cant_delete_item)
                        )
                        listener.onFailureListener()
                    }
                    .addOnSuccessListener { void ->
                        Log.d(TAG, "${AppConstance.ON_SUCCESS_LISTENER} : $void")
                        listener.onSuccessListener()
                    }
                    .addOnCompleteListener { task ->
                        Log.d(TAG, "${AppConstance.ON_COMPLETE_LISTENER} : $task")
                        listener.onCompleteListener()
                }
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }

    fun edit(
        context : Activity,
        TAG : String? = "",
        databaseReference: DatabaseReference,
        model : Any,
        listener : FireBaseListener){
        try{
            databaseReference.let { databaseReference ->
                databaseReference.setValue(model)
                    .addOnCanceledListener {
                        Log.e(TAG, AppConstance.ON_CANCEL_LISTENER)
                        listener.onCancelListener()
                    }
                    .addOnFailureListener {
                        Log.e(TAG, "${AppConstance.ON_FAILURE_LISTENER} : $it")
                        PopupMsg.alert(
                            context,
                            context.getString(R.string.msg_cant_edit_item)
                        )
                        listener.onFailureListener()
                    }
                    .addOnSuccessListener {
                        Log.d(TAG, "${AppConstance.ON_SUCCESS_LISTENER} : $it")
                        listener.onSuccessListener()
                    }
                    .addOnCompleteListener {
                        Log.d(TAG, "${AppConstance.ON_COMPLETE_LISTENER} : $it")
                        listener.onCompleteListener()
                    }
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }
}