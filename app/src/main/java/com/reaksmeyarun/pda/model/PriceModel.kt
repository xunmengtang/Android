package com.reaksmeyarun.pda.model

data class PriceModel (var id : String ?= "",
                  var dollar : String ?= "",
                  var khmer : String ?= "",
                  var size : SizeModel,
                  var currencyModel: CurrencyModel)