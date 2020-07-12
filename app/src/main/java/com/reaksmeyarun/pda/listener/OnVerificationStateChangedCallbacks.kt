package com.reaksmeyarun.pda.listener

import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

interface OnVerificationStateChangedCallbacks {
    fun onVerificationCompleted(phoneAuthCredential : PhoneAuthCredential)
    fun onVerificationFailed(e : FirebaseException)
    fun onCodeSent(s : String, forceResendingToken : PhoneAuthProvider.ForceResendingToken)
}