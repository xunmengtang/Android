package com.reaksmeyarun.pda.model

data class StockModel(var id : String ?= "",
                 var quantities : String ?= "",
                 var timestamp : String ?= "",
                 var startDate : String ?= "",
                 var endDate : String ?= "",
                 var option : String ?= "",
                 var description : String ?= "") {
}