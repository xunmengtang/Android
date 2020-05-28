package com.reaksmeyarun.pda.model

//data class CategoryModel(var id : String = ""){
data class CategoryModel(var id : String? = "",
                    var userSession : UserModel.UserSession,
                    var categoryInformation : CategoryInformation ,
                    var itemInformation : ItemModel.ItemInformation,
                    var timestamp : String ?= ""){
    class CategoryInformation(
        var id : String ?= "",
        var categoryName : String ?= "",
        var categoryDescription : String ?= ""
    )
}