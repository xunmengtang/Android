package com.reaksmeyarun.pda.base

import android.app.Activity
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.*
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.constance.FirebaseConstance
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.listener.FirebaseGetChildListener
import com.reaksmeyarun.pda.listener.FirebaseGetListener
import com.reaksmeyarun.pda.utils.PopupMsg

open class BaseFirebase {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun databaseReference(node : String? = "") : DatabaseReference{
        return firebaseDatabase.getReference(node!!)
    }
    val instancePhoneAuth = PhoneAuthProvider.getInstance()
    val instanceAuth = FirebaseAuth.getInstance()
    val instanceDatabaseReference = firebaseDatabase.reference

    open fun get(TAG: String? = "", databaseReference : DatabaseReference, listener : FirebaseGetListener) {
        try{
            databaseReference.let { it ->
                it.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.d(TAG, "${FirebaseConstance.DATABASE_ERROR} : $databaseError")
                        listener.onCancelListener(databaseError)
                    }
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        Log.i(TAG, "${FirebaseConstance.ON_DATA_CHANGED} : $dataSnapshot")
                        dataSnapshot.let { data ->
                            listener.onDataChange(data)
                        }
                    }
                })
            }
        }catch (ex : Exception){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }
    open fun gets(TAG: String?="", databaseReference: DatabaseReference, listener: FirebaseGetListener){
        try{
            databaseReference.let { it ->
                it.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.e(TAG, "${FirebaseConstance.DATABASE_ERROR} : $databaseError")
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
            Log.e(TAG, "${FirebaseConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }
    open fun getChild(activity : Activity, TAG : String?="", databaseReference : DatabaseReference, listener : FirebaseGetChildListener){
        try{
            databaseReference.addChildEventListener(object : ChildEventListener {
                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(TAG, "${FirebaseConstance.ON_CANCEL_LISTENER} : ${FirebaseConstance.DATABASE_ERROR} : $databaseError")
                    PopupMsg.alert(activity, activity.getString(R.string.msg_something_wrong))
                    listener.onCancelledListener(databaseError)
                }
                override fun onChildMoved(dataSnapshot: DataSnapshot, key: String?) {
                    Log.d(TAG, "${FirebaseConstance.ON_CHILD_MOVED} : $key | $dataSnapshot")
                    listener.onChildMoved(dataSnapshot)
                }
                override fun onChildChanged(dataSnapshot: DataSnapshot, key: String?) {
                    Log.d(TAG, "${FirebaseConstance.ON_CHILD_CHANGED} : $key | $dataSnapshot")
                    listener.onChildChanged(dataSnapshot)
                }
                override fun onChildAdded(dataSnapshot: DataSnapshot, key: String?) {
                    Log.d(TAG, "${FirebaseConstance.ON_CHILD_ADD} : $dataSnapshot")
                    listener.onChildAdded(dataSnapshot)
                }
                override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                    Log.d(TAG, "${FirebaseConstance.ON_CHILD_REMOVED} : $dataSnapshot")
                    listener.onChildRemoved(dataSnapshot)
                }
            })
        }catch (ex : Exception){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }
    open fun push(context : Activity, TAG : String? = "", databaseReference : DatabaseReference, map : MutableMap<String, Any>, listener : FireBaseListener){
        try{
            databaseReference.let { it ->
                map.let { mutableMap ->
                    it.updateChildren(mutableMap)
                        .addOnCanceledListener {
                            Log.e(TAG, FirebaseConstance.ON_CANCEL_LISTENER)
                        }.addOnFailureListener { exception ->
                            Log.e(TAG, "${FirebaseConstance.ON_FAILURE_LISTENER} : $exception")
                            listener.onFailureListener()
                            PopupMsg.alert(context, context.getString(R.string.msg_cant_push_item))
                        }.addOnSuccessListener { void ->
                            Log.d(TAG, "${FirebaseConstance.ON_SUCCESS_LISTENER} : $void")
                        }.addOnCompleteListener { task ->
                            Log.d(TAG, "${FirebaseConstance.ON_COMPLETE_LISTENER} : $task")
                            listener.onCompleteListener(task)
                        }
                }
            }
        }catch (ex : Exception){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }
    open fun delete(context : Activity, TAG : String? = "", databaseReference : DatabaseReference, listener : FireBaseListener){
        try{
            databaseReference.let { it ->
                it.removeValue()
                    .addOnCanceledListener {
                        Log.e(TAG, FirebaseConstance.ON_CANCEL_LISTENER)
                    }.addOnFailureListener { exception ->
                        Log.e(TAG, "${FirebaseConstance.ON_FAILURE_LISTENER} : $exception")
                        listener.onFailureListener()
                        PopupMsg.alert(context, context.getString(R.string.msg_cant_delete_item))
                    }.addOnSuccessListener { void ->
                        Log.d(TAG, "${FirebaseConstance.ON_SUCCESS_LISTENER} : $void")
                    }.addOnCompleteListener { task ->
                        Log.d(TAG, "${FirebaseConstance.ON_COMPLETE_LISTENER} : $task")
                        listener.onCompleteListener(task)
                    }
            }
        }catch (ex : Exception){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }
    open fun edit(context : Activity, TAG : String? = "", databaseReference: DatabaseReference, model : Any, listener : FireBaseListener){
        try{
            databaseReference.let { it ->
                it.setValue(model)
                    .addOnCanceledListener {
                        Log.e(TAG, FirebaseConstance.ON_CANCEL_LISTENER)
                    }.addOnFailureListener {
                        Log.e(TAG, "${FirebaseConstance.ON_FAILURE_LISTENER} : $it")
                        listener.onFailureListener()
                        PopupMsg.alert(context, context.getString(R.string.msg_cant_edit_item))
                    }.addOnSuccessListener {
                        Log.d(TAG, "${FirebaseConstance.ON_SUCCESS_LISTENER} : $it")
                    }.addOnCompleteListener {
                        Log.d(TAG, "${FirebaseConstance.ON_COMPLETE_LISTENER} : $it")
                        listener.onCompleteListener(it)
                    }
            }
        }catch (ex : Exception){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }
    open fun move(TAG : String? = "", activity: Activity, mutableMap : MutableMap<String, Any>, fromPath: DatabaseReference, toPath: DatabaseReference, listener: FireBaseListener) {
        try{
            push(activity, TAG, toPath, mutableMap, object : FireBaseListener {
                override fun onFailureListener() {
                    listener.onFailureListener()
                }
                override fun <TResult> onCompleteListener(task: Task<TResult>) {
                    Log.e(TAG, FirebaseConstance.ON_COMPLETE_LISTENER)
                    if(task.isSuccessful){
                        delete(
                            activity,
                            TAG,
                            fromPath,
                            object : FireBaseListener {
                                override fun onFailureListener() { /*  Do nothing */ }

                                override fun <TResult> onCompleteListener(task: Task<TResult>) {
                                    Log.e(TAG, FirebaseConstance.ON_COMPLETE_LISTENER)
                                    listener.onCompleteListener(task)
                                }
                            }
                        )
                    }
                }
            })
        }catch (ex : Exception){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${FirebaseConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }
    }
}