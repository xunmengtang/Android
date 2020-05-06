package com.ig.iginnovation.superapp.driver.mycustomclass

import androidx.lifecycle.MutableLiveData
import com.ig.iginnovation.superapp.driver.baseclass.BaseDataModel
import com.ig.iginnovation.superapp.driver.listener.DataModelPropertyChangeCallBack


class MyMutableLiveData<Model: BaseDataModel> : MutableLiveData<Model>(){
    override fun setValue(value: Model) {
        super.setValue(value)
        value.setPropertiesChangeCallBack(callback)
    }
    var callback: DataModelPropertyChangeCallBack = object :
        DataModelPropertyChangeCallBack {
        override fun onChanged() {
            setValue(value!!)
        }
    }
}