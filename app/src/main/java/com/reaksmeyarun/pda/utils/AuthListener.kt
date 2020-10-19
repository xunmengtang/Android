package com.reaksmeyarun.pda.utils

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}