package com.ig.iginnovation.superapp.driver.baseclass

import com.ig.iginnovation.superapp.driver.listener.DataModelPropertyChangeCallBack

open class BaseDataModel {
    lateinit var propertiesChangedCallback: DataModelPropertyChangeCallBack
    fun setPropertiesChangeCallBack(callBackDataModel: DataModelPropertyChangeCallBack){
        this.propertiesChangedCallback = callBackDataModel
    }
}