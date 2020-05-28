package com.reaksmeyarun.pda.datamodel

import com.reaksmeyarun.pda.base.BaseDataModel

class HomeDataModel : BaseDataModel(){
    companion object{
        const val HOME_P0210 = 1
        const val HOME_P0220 = 2
        const val HOME_P0230 = 3
        const val HOME_P0240 = 4
        const val HOME_P0250 = 5
        const val HOME_P0260 = 6
    }

    var state : Int? = HOME_P0210
    set(value) {
        field = value
        when(state){
            HOME_P0210 ->{
                showHomeP0210 = true
                showHomeP0220 = false
                showHomeP0230 = false
                showHomeP0240 = false
                showHomeP0250 = false
                showHomeP0260 = false
            }
            HOME_P0220 ->{
                showHomeP0210 = false
                showHomeP0220 = true
                showHomeP0230 = false
                showHomeP0240 = false
                showHomeP0250 = false
                showHomeP0260 = false
            }
            HOME_P0230 ->{
                showHomeP0210 = false
                showHomeP0220 = false
                showHomeP0230 = true
                showHomeP0240 = false
                showHomeP0250 = false
                showHomeP0260 = false
            }
            HOME_P0240 ->{
                showHomeP0210 = false
                showHomeP0220 = false
                showHomeP0230 = false
                showHomeP0240 = true
                showHomeP0250 = false
                showHomeP0260 = false
            }
            HOME_P0250 ->{
                showHomeP0210 = false
                showHomeP0220 = false
                showHomeP0230 = false
                showHomeP0240 = false
                showHomeP0250 = true
                showHomeP0260 = false
            }
            HOME_P0260 ->{
                showHomeP0210 = false
                showHomeP0220 = false
                showHomeP0230 = false
                showHomeP0240 = false
                showHomeP0250 = false
                showHomeP0260 = true
            }
        }
        propertiesChangedCallback.onChanged()
    }
    var showHomeP0210 : Boolean? = true
    set(value){
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showHomeP0220 : Boolean? = false
    set(value){
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showHomeP0230 : Boolean = false
    set(value){
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showHomeP0240 : Boolean = false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showHomeP0250 : Boolean = false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showHomeP0260 : Boolean = false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
}