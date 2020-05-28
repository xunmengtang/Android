package com.reaksmeyarun.pda.model

data class TicketModel(
    var id : String ?= "",
    var discount : DiscountModel,
    var cartModel: ArrayList<CartModel>
)