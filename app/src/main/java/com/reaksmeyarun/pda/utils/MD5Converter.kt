package com.reaksmeyarun.pda.utils

import java.math.BigInteger
import java.security.MessageDigest

object MD5Converter {

    fun md5(s: String) : String {
        return String.format(
            "%032x",
            BigInteger(1, MessageDigest.getInstance("MD5").digest(s.toByteArray(Charsets.UTF_8)))
        )
    }
}