package com.reaksmeyarun.pda.preference

import android.content.Context
import com.reaksmeyarun.pda.base.BasePreference
import com.reaksmeyarun.pda.constance.AppConstance

class UserSession(context: Context) : BasePreference(context) {
    override val preferenceName: String
        get() = "userSessionPreference"

    companion object {
        private var instance: UserSession?=null

        fun getInstance(context: Context): UserSession {
            if (instance == null)
                instance = UserSession(context)
            return instance!!
        }
    }

    fun getToken():String{
        return get(AppConstance.USER_TOKEN,"").toString()
    }

    fun setToken(lang:String){
        save(AppConstance.USER_TOKEN,lang)
    }
}