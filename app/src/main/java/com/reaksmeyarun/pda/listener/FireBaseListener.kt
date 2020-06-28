package com.reaksmeyarun.pda.listener

import com.google.android.gms.tasks.Task

interface FireBaseListener{
//    fun onCancelListener()
    fun onFailureListener()
//    fun onSuccessListener()
    fun <TResult> onCompleteListener(task: Task<TResult>)
}