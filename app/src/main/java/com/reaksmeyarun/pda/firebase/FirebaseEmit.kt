package com.reaksmeyarun.pda.firebase

import android.app.Activity
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.CLICK_LISTENER
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.DATABASE_ERROR
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.EXCEPTION
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.EXCEPTION_IN_INITIALIZER_ERROR
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_CANCEL_LISTENER
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_CHILD_ADD
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_CHILD_CHANGED
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_CHILD_MOVED
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_CHILD_REMOVED
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_COMPLETE_LISTENER
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_DATA_CHANGED
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_FAILURE_LISTENER
import com.reaksmeyarun.pda.constance.FirebaseConstance.Companion.ON_SUCCESS_LISTENER
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.listener.FirebaseGetChildListener
import com.reaksmeyarun.pda.listener.FirebaseGetListener
import com.reaksmeyarun.pda.listener.StaffSignInListener
import com.reaksmeyarun.pda.model.StaffModel
import com.reaksmeyarun.pda.utils.DataSnapShotConvertUtils
import com.reaksmeyarun.pda.utils.MD5Converter.MD5
import com.reaksmeyarun.pda.utils.PopupMsg


@Suppress("NAME_SHADOWING")
class FirebaseEmit {

    fun get(
        TAG: String? = "",
        databaseReference : DatabaseReference,
        listener : FirebaseGetListener
    ) {
        try{
            databaseReference.let { databaseReference ->
                databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.d(TAG, "$DATABASE_ERROR : $databaseError")
                        listener.onCancelListener(databaseError)
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        Log.i(TAG, "$ON_DATA_CHANGED : $dataSnapshot")
                        dataSnapshot.let { data ->
                            listener.onDataChange(data)
                        }
                    }
                })
            }
        }catch (ex : Exception){
            Log.e(TAG, "$EXCEPTION : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "$EXCEPTION_IN_INITIALIZER_ERROR : $ex")
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
                        Log.e(TAG, "$DATABASE_ERROR : $databaseError")
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
            Log.e(TAG, "$EXCEPTION : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "$EXCEPTION_IN_INITIALIZER_ERROR : $ex")
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
                    Log.e(TAG, "$ON_CANCEL_LISTENER : $DATABASE_ERROR : $databaseError")
                    PopupMsg.alert(activity, activity.getString(R.string.msg_something_wrong))
                    listener.onCancelledListener(databaseError)
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot, key: String?) {
                    Log.d(TAG, "$ON_CHILD_MOVED : $key | $dataSnapshot")
                    listener.onChildMoved(dataSnapshot)
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot, key: String?) {
                    Log.d(TAG, "$ON_CHILD_CHANGED : $key | $dataSnapshot")
                    listener.onChildChanged(dataSnapshot)
                }

                override fun onChildAdded(dataSnapshot: DataSnapshot, key: String?) {
                    Log.d(TAG, "$ON_CHILD_ADD : $dataSnapshot")
                    listener.onChildAdded(dataSnapshot)
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                    Log.d(TAG, "$ON_CHILD_REMOVED : $dataSnapshot")
                    listener.onChildRemoved(dataSnapshot)
                }
            })
        }catch (ex : Exception){
            Log.e(TAG, "$EXCEPTION : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "$EXCEPTION_IN_INITIALIZER_ERROR : $ex")
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
                            Log.e(TAG, ON_CANCEL_LISTENER)
                        }
                        .addOnFailureListener { exception ->
                            Log.e(TAG, "$ON_FAILURE_LISTENER : $exception")
                            listener.onFailureListener()
                            PopupMsg.alert(context, context.getString(R.string.msg_cant_push_item))
                        }
                        .addOnSuccessListener { void ->
                            Log.d(TAG, "$ON_SUCCESS_LISTENER : $void")
                        }
                        .addOnCompleteListener { task ->
                            Log.d(TAG, "$ON_COMPLETE_LISTENER : $task")
                            listener.onCompleteListener(task)
                        }
                }
            }
        }catch (ex : Exception){
            Log.e(TAG, "$EXCEPTION : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "$EXCEPTION_IN_INITIALIZER_ERROR : $ex")
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
                        Log.e(TAG, ON_CANCEL_LISTENER)
                    }
                    .addOnFailureListener { exception ->
                        Log.e(TAG, "$ON_FAILURE_LISTENER : $exception")
                        listener.onFailureListener()
                        PopupMsg.alert(context, context.getString(R.string.msg_cant_delete_item))
                    }
                    .addOnSuccessListener { void ->
                        Log.d(TAG, "$ON_SUCCESS_LISTENER : $void")
                    }
                    .addOnCompleteListener { task ->
                        Log.d(TAG, "$ON_COMPLETE_LISTENER : $task")
                        listener.onCompleteListener(task)
                }
            }
        }catch (ex : Exception){
            Log.e(TAG, "$EXCEPTION : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "$EXCEPTION_IN_INITIALIZER_ERROR : $ex")
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
                        Log.e(TAG, ON_CANCEL_LISTENER)
                    }
                    .addOnFailureListener {
                        Log.e(TAG, "$ON_FAILURE_LISTENER : $it")
                        listener.onFailureListener()
                        PopupMsg.alert(context, context.getString(R.string.msg_cant_edit_item))
                    }
                    .addOnSuccessListener {
                        Log.d(TAG, "$ON_SUCCESS_LISTENER : $it")
                    }
                    .addOnCompleteListener {
                        Log.d(TAG, "$ON_COMPLETE_LISTENER : $it")
                        listener.onCompleteListener(it)
                    }
            }
        }catch (ex : Exception){
            Log.e(TAG, "$EXCEPTION : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "$EXCEPTION_IN_INITIALIZER_ERROR : $ex")
        }
    }

    fun move(
        TAG : String? = "",
        activity: Activity,
        mutableMap : MutableMap<String, Any>,
        fromPath: DatabaseReference,
        toPath: DatabaseReference,
        listener: FireBaseListener
    ) {
        try{
            push(activity, TAG, toPath, mutableMap, object : FireBaseListener{
                    override fun onFailureListener() {
                        listener.onFailureListener()
                    }
                    override fun <TResult> onCompleteListener(task: Task<TResult>) {
                        Log.e(TAG, ON_COMPLETE_LISTENER)
                        if(task.isSuccessful){
                            delete(
                                activity,
                                TAG,
                                fromPath,
                                object : FireBaseListener{
                                    override fun onFailureListener() { /*  Do nothing */ }

                                    override fun <TResult> onCompleteListener(task: Task<TResult>) {
                                        Log.e(TAG, ON_COMPLETE_LISTENER)
                                        listener.onCompleteListener(task)
                                    }
                                }
                            )
                        }
                    }
                })
        }catch (ex : Exception){
            Log.e(TAG, "$EXCEPTION : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "$EXCEPTION_IN_INITIALIZER_ERROR : $ex")
        }
    }

    fun signUp(
        TAG: String?= "",
        firebaseAuth : FirebaseAuth,
        email : String ?= "",
        password : String ?= "",
        listener: FireBaseListener){
        try{
            firebaseAuth
                .createUserWithEmailAndPassword(email!!, password!!)
                    .addOnCanceledListener {
                        Log.e(TAG, ON_CANCEL_LISTENER)
                    }
                    .addOnFailureListener {
                        listener.onFailureListener()
                        Log.e(TAG, "$ON_FAILURE_LISTENER : $it")
                    }
                    .addOnSuccessListener {
                        Log.d(TAG, "$ON_SUCCESS_LISTENER : $it")
                    }
                    .addOnCompleteListener {
                        Log.d(TAG, "$ON_COMPLETE_LISTENER : $it")
                        listener.onCompleteListener(it)
                    }
        }catch (ex : Exception){
            Log.e(TAG, "$EXCEPTION : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "$EXCEPTION_IN_INITIALIZER_ERROR : $ex")
        }
    }

    fun sendEmailVerification(
        TAG: String?= "",
        firebaseAuth : FirebaseAuth,
        listener: FireBaseListener
    ){
        try{
            firebaseAuth
                .currentUser!!
                .sendEmailVerification()
                .addOnCanceledListener {
                    Log.e(TAG, ON_CANCEL_LISTENER)
                }
                .addOnFailureListener {
                    listener.onFailureListener()
                    Log.i(TAG, "$ON_FAILURE_LISTENER : $it")
                }
                .addOnSuccessListener {
                    Log.i(TAG, "$ON_SUCCESS_LISTENER : $it")
                }
                .addOnCompleteListener {
                    Log.i(TAG, "$CLICK_LISTENER : $it")
                    listener.onCompleteListener(it)
                }
        }catch (ex : Exception){
            Log.e(TAG, "$EXCEPTION : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "$EXCEPTION_IN_INITIALIZER_ERROR : $ex")
        }
    }

    fun signIn(
        TAG: String?="",
        email: String?="",
        pass: String?="",
        firebaseAuth: FirebaseAuth,
        listener: FireBaseListener
    ){
        firebaseAuth
            .signInWithEmailAndPassword(email!!, pass!!)
                .addOnCanceledListener {
                    Log.e(TAG, ON_CANCEL_LISTENER)
                }
                .addOnFailureListener {
                    listener.onFailureListener()
                    Log.d(TAG, "$ON_FAILURE_LISTENER : $it")
                }
                .addOnSuccessListener {
                    Log.d(TAG, "$ON_SUCCESS_LISTENER : $it")
                }
                .addOnCompleteListener {
                    Log.d(TAG, "$ON_COMPLETE_LISTENER : $it")
                    listener.onCompleteListener(it)
                }

    }

    fun staffSignIn(
        TAG : String?="",
        email: String?,
        pass: String?,
        databaseReference: DatabaseReference,
        signInListener : StaffSignInListener
    ){
        try{
            get(
                TAG,
                databaseReference,
                object : FirebaseGetListener{
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if(signInStaffMethod(TAG, email, pass, DataSnapShotConvertUtils.dataSnapShotToArrayList(StaffModel::class.java, dataSnapshot))){
                            Log.d(TAG, ON_SUCCESS_LISTENER)
                            signInListener.onSuccess()
                        }else {
                            Log.e(TAG, ON_FAILURE_LISTENER)
                            signInListener.onFailure()
                        }
                    }

                    override fun onCancelListener(databaseError: DatabaseError) {

                    }
                }
            )
        }catch (ex : Exception){
            Log.e(TAG, "$EXCEPTION : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "$EXCEPTION_IN_INITIALIZER_ERROR : $ex")
        }
    }

    private fun signInStaffMethod(TAG : String?, email: String?, pass: String?, staffModelList : List<StaffModel>) : Boolean{
        try {
            for (i in staffModelList){
                return if(i.userInformation.accountLogin.email == email){
                    i.userInformation.accountLogin.password == MD5(pass!!)
                }else
                    false
            }
        }catch (ex : Exception){
            Log.e(TAG, "$EXCEPTION : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "$EXCEPTION_IN_INITIALIZER_ERROR : $ex")
        }
        return false
    }

    fun resetPassword(TAG : String ?= "", firebaseAuth : FirebaseAuth, email : String ?= "", listener : FireBaseListener){
        try{
            firebaseAuth.sendPasswordResetEmail(email!!)
                .addOnCanceledListener {
                    Log.e(TAG, ON_CANCEL_LISTENER)
                }
                .addOnFailureListener {
                    Log.e(TAG, "$ON_FAILURE_LISTENER : $it")
                    listener.onFailureListener()
                }
                .addOnCompleteListener {
                    Log.i(TAG, "$ON_COMPLETE_LISTENER : $it")
                    listener.onCompleteListener(it)
                }
                .addOnSuccessListener {
                    Log.i(TAG, "$ON_SUCCESS_LISTENER : $it")
                }
        }catch (ex : Exception){
            Log.e(TAG, "$EXCEPTION : $ex")
        }catch (Ex : ExceptionInInitializerError){
            Log.e(TAG, "$EXCEPTION_IN_INITIALIZER_ERROR : $Ex")
        }
    }
}