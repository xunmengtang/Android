package com.reaksmeyarun.pda.model

data class ProductItemModel(var id : String ?= "",
                       var userSession : UserModel.UserSession,
                       var item : ItemModel,
                       var barCode : String ?= "",
                       var quantities : String ?= "",
                       var timestamp : String ?= "",
                       var startDate : String ?= "",
                       var endDate : String ?= "",
                       var option : String ?= "",
                       var description : String ?= "") {
}