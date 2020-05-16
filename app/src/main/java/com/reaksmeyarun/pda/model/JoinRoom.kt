package com.reaksmeyarun.pda.model

class JoinRoom(var roomId : String ?= "") {
    class ItemPending(var id : String ?= "",
                      var roomId : String ?= "",
                      var cartModel : CartModel)
    class ItemEdit(var id : String ?= "",
                   var roomId : String ?= "",
                   var cartModel : CartModel)
    class ItemSubmit(var id : String ?= "",
                     var roomId : String ?= "" ,
                     var cartModel : CartModel)
    class TransactionPaymentSuccess(var id : String ?= "",
                                    var roomId : String ?= "",
                                    var userSession : UserModel.UserSession,
                                    var cartModel: ArrayList<CartModel>)
}