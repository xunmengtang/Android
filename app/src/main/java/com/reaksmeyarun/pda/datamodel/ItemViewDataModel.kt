package com.reaksmeyarun.pda.datamodel

import com.reaksmeyarun.pda.base.BaseDataModel

class ItemViewDataModel : BaseDataModel() {

    companion object{
        const val ITEM_I0110 = 1
        const val ITEM_I0200 = 2
        const val ITEM_I0210 = 3
        const val ITEM_I0300 = 4
        const val ITEM_I0310 = 5
    }

    var state : Int? = ITEM_I0110
        set(value) {
            field = value
            when(state){
                ITEM_I0110 ->{
                    showItemI0110 = true
                    showItemI0200 = false
                    showItemI0210 = false
                    showItemI0300 = false
                    showItemI0310 = false
                }
                ITEM_I0200 ->{
                    showItemI0110 = false
                    showItemI0200 = true
                    showItemI0210 = false
                    showItemI0300 = false
                    showItemI0310 = false
                }
                ITEM_I0210 ->{
                    showItemI0110 = false
                    showItemI0200 = false
                    showItemI0210 = true
                    showItemI0300 = false
                    showItemI0310 = false
                }
                ITEM_I0300 ->{
                    showItemI0110 = false
                    showItemI0200 = false
                    showItemI0210 = false
                    showItemI0300 = true
                    showItemI0310 = false
                }
                ITEM_I0310 ->{
                    showItemI0110 = false
                    showItemI0200 = false
                    showItemI0210 = false
                    showItemI0300 = false
                    showItemI0310 = true
                }
            }
            propertiesChangedCallback.onChanged()
        }
    var showItemI0110 : Boolean? = true
        set(value){
            field = value
            propertiesChangedCallback.onChanged()
        }
    var showItemI0200 : Boolean? = false
        set(value){
            field = value
            propertiesChangedCallback.onChanged()
        }
    var showItemI0210 : Boolean? = false
        set(value){
            field = value
            propertiesChangedCallback.onChanged()
        }
    var showItemI0300 : Boolean? = false
        set(value){
            field = value
            propertiesChangedCallback.onChanged()
        }
    var showItemI0310 : Boolean? = false
        set(value){
            field = value
            propertiesChangedCallback.onChanged()
        }
}