package com.reaksmeyarun.pda.model

class ItemOnTransaction{
    var id : String ?= ""
    var item = StockItemModel.StockItemInformationModel()
    var quantities : String ?= "0"
    var sweetModel = SweetModel()
}