package com.reaksmeyarun.pda.listener

interface FireBaseListener{
    fun onCancelListener()
    fun onFailureListener()
    fun onSuccessListener()
    fun onCompleteListener()
}