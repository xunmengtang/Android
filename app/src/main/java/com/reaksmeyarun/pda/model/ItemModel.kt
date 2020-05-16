package com.reaksmeyarun.pda.model

class ItemModel (var id : String = "") {
//class ItemModel (var id : String = "",
//                 var userSession : UserModel.UserSession,
//                 var itemInformation : ItemInformation,
//                 var categoryInformation: CategoryModel.CategoryInformation,
//                 var priceModel: PriceModel,
//                 var costModel: CostModel,
//                 var vat : String ?= "",
//                 var timestamp : String){
    class ItemInformation(var id : String ?= "",
                          var itemName : String ?= "",
                          var itemID : String ?= "",
                          var itemDescription : String ?= "",
                          var timestamp : String ?= "")
}