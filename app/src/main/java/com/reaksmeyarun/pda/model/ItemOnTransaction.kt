package com.reaksmeyarun.pda.model

data class ItemOnTransaction(var id : String ?= "",
                        var item : ItemModel,
                        var quantities : String ?= "0",
                        var sweetModel : SweetModel)