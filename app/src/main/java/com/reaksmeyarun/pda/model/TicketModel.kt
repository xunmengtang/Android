package com.reaksmeyarun.pda.model

class TicketModel(
    var id : String ?= "",
    var discount : DiscountModel,
    var cartModel: ArrayList<CartModel>
)