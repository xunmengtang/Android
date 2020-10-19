package com.reaksmeyarun.pda.utils

import java.util.*

object MapDataUtils {
    fun <Any>objectToMap(key : String = "", data : Any?) : MutableMap<String, Any> {
        val dataMap : MutableMap<String, Any> = HashMap()
        dataMap[key] = data as Any
        return dataMap
    }
}