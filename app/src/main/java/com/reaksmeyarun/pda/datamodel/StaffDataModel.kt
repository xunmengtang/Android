package com.reaksmeyarun.pda.datamodel

import com.reaksmeyarun.pda.base.BaseDataModel

class StaffDataModel : BaseDataModel(){

    var firstName : String ?= "N/A"
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var lastName : String ?= "N/A"
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var email : String ?= "N/A"
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var phoneNumber : String ?= "N/A"
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var dateOfBirth : String ?= "N/A"
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var birthOfPlace : String ?= "N/A"
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }

}