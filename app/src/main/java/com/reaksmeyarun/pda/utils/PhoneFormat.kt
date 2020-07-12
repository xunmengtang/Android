package com.reaksmeyarun.pda.utils

import android.telephony.PhoneNumberUtils

object PhoneFormat {
    fun local(s : String) : String = "+855${PhoneNumberUtils.formatNumber(s, "KH")}"
}