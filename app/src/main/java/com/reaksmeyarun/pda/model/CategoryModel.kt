package com.reaksmeyarun.pda.model

class CategoryModel(var id : String = ""){
//class CategoryModel(var id : String = "",
//                    var userSession : UserModel.UserSession,
//                    var categoryInformation : CategoryInformation ,
//                    var itemInformation : ItemModel.ItemInformation){
    class CategoryInformation(var id : String ?= "",
                              var categoryName : String ?= "",
                              var categoryDescription : String ?= "",
                              var timestamp : String ?= "")
}