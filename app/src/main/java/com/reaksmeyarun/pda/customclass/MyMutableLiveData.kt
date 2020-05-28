package com.reaksmeyarun.pda.customclass

import androidx.lifecycle.MutableLiveData
import com.reaksmeyarun.pda.base.BaseDataModel
import com.reaksmeyarun.pda.listener.DataModelPropertyChangeCallBack


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