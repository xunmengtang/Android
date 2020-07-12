package com.reaksmeyarun.pda.datamodel

import com.google.firebase.auth.PhoneAuthProvider

object CodeSentDataModel {
    var verificationId : String?= ""
    var token : PhoneAuthProvider.ForceResendingToken?= null
}