package com.reaksmeyarun.pda.model

data class JoinRoom(var roomId : String ?= "") {
    data class ItemPending(var id : String ?= "",
                      var roomId : String ?= "",
                      var cartModel : CartModel)
    data class ItemEdit(var id : String ?= "",
                   var roomId : String ?= "",
                   var cartModel : CartModel)
    data class ItemSubmit(var id : String ?= "",
                     var roomId : String ?= "" ,
                     var cartModel : CartModel)
    data class TransactionPaymentSuccess(var id : String ?= "",
                                    var roomId : String ?= "",
                                    var userSession : UserModel.UserSession,
                                    var ticket : TicketModel)
}