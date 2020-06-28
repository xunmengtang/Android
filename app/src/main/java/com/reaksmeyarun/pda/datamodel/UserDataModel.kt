package com.reaksmeyarun.pda.datamodel

import com.reaksmeyarun.pda.base.BaseDataModel

class UserDataModel : BaseDataModel() {
    companion object{
        const val USER_Z0410 = 1
        const val USER_Z0420 = 2
        const val USER_Z0430 = 3
    }

    var state : Int? = USER_Z0410
        set(value) {
            field = value
            when(state){
                USER_Z0410 ->{
                    showUserZ0410 = true
                    showUserZ0420 = false
                    showUserZ0430 = false
                }
                USER_Z0420 ->{
                    showUserZ0410 = false
                    showUserZ0420 = true
                    showUserZ0430 = false
                }
                USER_Z0430 ->{
                    showUserZ0410 = false
                    showUserZ0420 = false
                    showUserZ0430 = true
                }
            }
            propertiesChangedCallback.onChanged()
        }
    var showUserZ0410 : Boolean? = true
        set(value){
            field = value
            propertiesChangedCallback.onChanged()
        }
    var showUserZ0420 : Boolean ?= false
        set(value) {
            field = value
            propertiesChangedCallback.onChanged()
        }
    var showUserZ0430 : Boolean ?= false
        set(value) {
            field = value
            propertiesChangedCallback.onChanged()
        }
}