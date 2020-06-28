package com.reaksmeyarun.pda.datamodel

import com.reaksmeyarun.pda.base.BaseDataModel

class SignInDataModel : BaseDataModel(){
    companion object{
//        Skin
        const val SIGN_IN_0100_CONTENT_EMAIL = 1
        const val SIGN_IN_0100_CONTENT_PASSWORD = 2
    }

    var state : Int ?= SIGN_IN_0100_CONTENT_EMAIL
    set(value) {
        field = value
        when(state){
            SIGN_IN_0100_CONTENT_EMAIL ->{
                showSignIn0100ContentEmail = true
                showSignInP0100ContentPassword = false
             }
            SIGN_IN_0100_CONTENT_PASSWORD ->{
                showSignIn0100ContentEmail = false
                showSignInP0100ContentPassword = true
            }
        }
        propertiesChangedCallback.onChanged()
    }
    var showSignIn0100ContentEmail : Boolean ?= true
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }

    var showSignInP0100ContentPassword : Boolean ?= false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }

    var showToolBar : Boolean = false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }

    var showProgress : Boolean = false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
}