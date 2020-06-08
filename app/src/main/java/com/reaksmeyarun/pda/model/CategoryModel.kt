package com.reaksmeyarun.pda.model

class CategoryModel{
    var userSession = UserModel.UserSession()
    var categoryInformation = CategoryInformation()
//    var itemInformation = ArrayList<ItemModel.ItemInformation>()
    var timestamp : String ?= ""
    var status : Int ?= 0

     class CategoryInformation {
         var id: String? = ""
         var categoryName: String? = ""
         var categoryDescription: String? = ""

         override fun toString(): String {
             return "CategoryInformation(id=$id, categoryName=$categoryName, categoryDescription=$categoryDescription)"
         }

     }

    override fun toString(): String {
        return "CategoryModel(userSession=$userSession, categoryInformation=$categoryInformation, timestamp=$timestamp)"
    }

}