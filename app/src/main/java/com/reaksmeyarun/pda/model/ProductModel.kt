package com.reaksmeyarun.pda.model

data class ProductModel(var id : String ?= "",
                   var userSession: UserModel.UserSession,
                   var productCategoryModel: ProductCategoryModel,
                   var productItemModel: ProductItemModel)