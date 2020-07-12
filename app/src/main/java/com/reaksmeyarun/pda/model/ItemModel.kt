package com.reaksmeyarun.pda.model

class ItemModel {
    var userSession = UserModel.UserSession()
    var itemInformation = ItemInformationModel()
    var categoryInformation = CategoryInformationModel()
    var timestamp : String ?= ""
    override fun toString(): String = "ItemModel(userSession=$userSession, itemInformation=$itemInformation, categoryInformation=$categoryInformation, timestamp=$timestamp)"
}