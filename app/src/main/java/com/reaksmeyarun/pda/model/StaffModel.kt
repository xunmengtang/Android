package com.reaksmeyarun.pda.model

import com.reaksmeyarun.pda.constance.AppConstance

class StaffModel{
    var userSession = UserSession()
    var userInformation = UserInformation()
    var status : String ?= ""

    class UserSession{
        var id : String ?= ""
        var token : String ?= ""
    }

    class UserInformation{
        var id : String ?= ""
        var icon : String ?= AppConstance.NO_IMAGE_URL
        var firstName : String ?= ""
        var lastName : String ?= ""
        var gender : String ?= "0"
        var birthOfDate : String ?= ""
        var birthOfPlace : String ?= ""
        var phoneNumber : String ?= ""
        var emailAddress : String ?= ""
        var accountLogin = AccountLogin()
        var address : String ?= ""
        var rule = Rule()
    }

    class Rule{
        var rule : String ?= "0"
        var title : String ?= ""
    }

    class AccountLogin{
        var email : String?= ""
        var password : String ?= ""
    }
}