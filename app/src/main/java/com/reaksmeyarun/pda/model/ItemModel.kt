package com.reaksmeyarun.pda.model

import java.io.ObjectInput

//data class ItemModel (var id : String? = "") {
data class ItemModel (var id : String? = "",
                 var userSession : UserModel.UserSession,
                 var itemInformation : ItemInformation,
                 var categoryInformation: CategoryModel.CategoryInformation,
//                 var priceModel: PriceModel,
//                 var costModel: CostModel,
//                 var vat : String ?= "",
                 var timestamp : String ?= "") {
    data class ItemInformation(var id : String ?= "",
                               var itemName : String ?= "",
                               var itemCode : String ?= "",
                               var iconUrl : String ?= "",
                               var itemDescription : String ?= "")
}