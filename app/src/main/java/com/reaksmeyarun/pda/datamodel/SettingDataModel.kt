package com.reaksmeyarun.pda.datamodel

import com.reaksmeyarun.pda.base.BaseDataModel

class SettingDataModel : BaseDataModel() {
    companion object{
        const val SETTING_Z0210 = 1
        const val SETTING_Z0220 = 2
        const val SETTING_Z0230 = 3
        const val SETTING_Z0231 = 4
        const val SETTING_Z0240 = 5
        const val SETTING_Z0241 = 6
    }
    var state : Int? = SETTING_Z0210
    set(value) {
        field = value
        when(state){
            SETTING_Z0210 ->{
                showSettingZ0210 = true
                showSettingZ0220 = false
                showSettingZ0230 = false
                showSettingZ0231 = false
                showSettingZ0240 = false
                showSettingZ0241 = false
            }
            SETTING_Z0220 ->{
                showSettingZ0210 = false
                showSettingZ0220 = true
                showSettingZ0230 = false
                showSettingZ0231 = false
                showSettingZ0240 = false
                showSettingZ0241 = false
            }
            SETTING_Z0230 ->{
                showSettingZ0210 = false
                showSettingZ0220 = false
                showSettingZ0230 = true
                showSettingZ0231 = false
                showSettingZ0240 = false
                showSettingZ0241 = false
            }
            SETTING_Z0231 ->{
                showSettingZ0210 = false
                showSettingZ0220 = false
                showSettingZ0230 = false
                showSettingZ0231 = true
                showSettingZ0240 = false
                showSettingZ0241 = false
            }
            SETTING_Z0240 ->{
                showSettingZ0210 = false
                showSettingZ0220 = false
                showSettingZ0230 = false
                showSettingZ0231 = false
                showSettingZ0240 = true
                showSettingZ0241 = false
            }
            SETTING_Z0241 -> {
                showSettingZ0210 = false
                showSettingZ0220 = false
                showSettingZ0230 = false
                showSettingZ0231 = false
                showSettingZ0240 = false
                showSettingZ0241 = true
            }
        }
        propertiesChangedCallback.onChanged()
    }

    var showSettingZ0210 : Boolean ?= true
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showSettingZ0220 : Boolean ?= false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showSettingZ0230 : Boolean ?= false
    set(value){
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showSettingZ0231 : Boolean ?= false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showSettingZ0240 : Boolean ?= false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showSettingZ0241 : Boolean ?= false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
}