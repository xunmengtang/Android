package com.reaksmeyarun.pda.model

class ItemInformationModel {
    var id : String ?= ""
    var itemName : String ?= ""
    var itemCode : String ?= ""
    var iconUrl : String ?= ""
    var itemDescription : String ?= ""
    var price : String ?= ""
    var cost : String ?= ""
    override fun toString() : String = "ItemInformation(id=$id, itemName=$itemName, itemCode=$itemCode, iconUrl=$iconUrl, itemDescription=$itemDescription, price=$price, cost=$cost)"
}