package com.reaksmeyarun.pda.base

import com.reaksmeyarun.pda.listener.DataModelPropertyChangeCallBack

open class BaseDataModel {
    lateinit var propertiesChangedCallback: DataModelPropertyChangeCallBack
    fun setPropertiesChangeCallBack(callBackDataModel: DataModelPropertyChangeCallBack){
        this.propertiesChangedCallback = callBackDataModel
    }
}