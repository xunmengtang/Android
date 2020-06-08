package com.reaksmeyarun.pda.model

class ItemModel {
    var userSession = UserModel.UserSession()
    var itemInformation = ItemInformation()
    var categoryInformation = CategoryModel.CategoryInformation()
    var timestamp : String ?= ""

    class ItemInformation{
        var id : String ?= ""
        var itemName : String ?= ""
        var itemCode : String ?= ""
        var iconUrl : String ?= ""
        var itemDescription : String ?= ""

        override fun toString(): String {
            return "ItemInformation(id=$id, itemName=$itemName, itemCode=$itemCode, iconUrl=$iconUrl, itemDescription=$itemDescription)"
        }

    }

    override fun toString(): String {
        return "ItemModel(userSession=$userSession, itemInformation=$itemInformation, categoryInformation=$categoryInformation, timestamp=$timestamp)"
    }
}