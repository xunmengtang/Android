package com.reaksmeyarun.pda.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.json.JSONException
import org.json.JSONObject

class ConvertUtils private constructor() {
    fun stringToObject(strJSon: String?, cls: Class<*>?): Any? {
        return try {
            Gson().fromJson(JsonParser().parse(strJSon), cls)
        } catch (e: Exception) {
            Log.i("ConvertUtils", "Json format not much with mapping class")
            null
        }
    }

    fun objectToJson(`object`: Any?): JSONObject? {
        val gson = Gson()
        return if (`object` != null) {
            try {
                Log.i("ConvertUtils", "Success")
                JSONObject(gson.toJson(`object`))
            } catch (e: JSONException) {
                Log.i("ConvertUtils", e.message)
                null
            }
        } else {
            Log.i("ConvertUtils", "Can not convert null object to json")
            null
        }
    }

    companion object {
        private val instance: ConvertUtils? = null
        fun init(): ConvertUtils {
            return instance ?: ConvertUtils()
        }
    }
}