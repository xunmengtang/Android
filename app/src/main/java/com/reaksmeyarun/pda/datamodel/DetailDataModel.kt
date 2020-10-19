package com.reaksmeyarun.pda.datamodel

import com.reaksmeyarun.pda.base.BaseDataModel

class DetailDataModel : BaseDataModel(){

    var itemName : String? = "N/A"
    set(value){
        field = value
        propertiesChangedCallback.onChanged()
    }

    var itemDesc : String? = "N/A"
    set(value){
        field = value
        propertiesChangedCallback.onChanged()
    }

    var itemPrice : String? = "$ 0.0"
    set(value){
        field = "$ $value"
        propertiesChangedCallback.onChanged()
    }
}