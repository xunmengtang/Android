package com.reaksmeyarun.pda.listener

import com.google.android.gms.tasks.Task

interface FireBaseListener{
    fun onFailureListener()
    fun <TResult> onCompleteListener(task: Task<TResult>)
}