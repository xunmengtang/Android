package com.reaksmeyarun.pda.datamodel

import com.reaksmeyarun.pda.base.BaseDataModel

class InboxDataModel : BaseDataModel() {
    companion object{
        const val INBOX_P0310 = 1
        const val INBOX_P0320 = 2
    }

    var state : Int? = INBOX_P0310
    set(value) {
        field = value
        when(state){
            INBOX_P0310 ->{
                showInboxP0310 = true
                showInboxP0320 = false
            }
            INBOX_P0320 ->{
                showInboxP0310 = false
                showInboxP0320 = true
            }
        }
        propertiesChangedCallback.onChanged()
    }
    var showInboxP0310 : Boolean = true
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showInboxP0320 : Boolean = false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
}