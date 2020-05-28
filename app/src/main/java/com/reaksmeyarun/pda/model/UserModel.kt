package com.reaksmeyarun.pda.model

data class UserModel(var id : String ?= "",
                var userSession: UserSession,
                var name : String ?= "",
                var birthOfDate : String ?= "",
                var birthOfPlace : String ?= "",
                var phoneNumber : String ?= "",
                var emailAddress : String,
                var address : String ?= "",
                var rule : String ?= ""){

                data class UserSession(var id : String ?= "",
                                  var token : String ?= "")
}