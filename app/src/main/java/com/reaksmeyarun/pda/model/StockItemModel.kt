package com.reaksmeyarun.pda.model

class StockItemModel {
    var userSession = UserModel.UserSession()
    var stockItemInformationModel = StockItemInformationModel()
    var timestamp : String ?= ""
    var startDate : String ?= ""
    var endDate : String ?= ""

    class StockItemInformationModel{
        var id : String ?= ""
        var categoryInformation = CategoryModel.CategoryInformation()
        var itemInformationModel = ItemModel.ItemInformation()
        var option : String ?= ""
        var quantities : String ?= ""
        var barCode : String ?= ""
        var cost : String ?= ""
        var price : String ?= ""
        var description : String ?= ""
        var status : String ?= ""
    }
}