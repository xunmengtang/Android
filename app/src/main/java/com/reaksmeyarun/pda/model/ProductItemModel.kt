package com.reaksmeyarun.pda.model

class ProductItemModel {
    var id : String ?= ""
    var userSession = UserModel.UserSession()
    var stockItemInformationModel = StockItemModel.StockItemInformationModel()
    var quantities : String ?= ""
    var timestamp : String ?= ""
    var status : String ?= ""
}