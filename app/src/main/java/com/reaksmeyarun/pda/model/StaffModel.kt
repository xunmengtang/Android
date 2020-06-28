package com.reaksmeyarun.pda.model

import com.reaksmeyarun.pda.constance.AppConstance

class StaffModel{
    var userSession = UserSession()
    var userInformation = UserInformation()
    var status : String ?= ""

    class UserSession{
        var id : String ?= ""
        var token : String ?= ""

        override fun toString(): String {
            return "UserSession(id=$id, token=$token)"
        }
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

        override fun toString(): String {
            return "UserInformation(id=$id, icon=$icon, firstName=$firstName, lastName=$lastName, gender=$gender, birthOfDate=$birthOfDate, birthOfPlace=$birthOfPlace, phoneNumber=$phoneNumber, emailAddress=$emailAddress, accountLogin=$accountLogin, address=$address, rule=$rule)"
        }
    }

    class Rule{
        var rule : String ?= "0"
        var title : String ?= ""

        override fun toString(): String {
            return "Rule(rule=$rule, title=$title)"
        }
    }

    class AccountLogin{
        var email : String?= ""
        var password : String ?= ""

        override fun toString(): String {
            return "AccountLogin(email=$email, password=$password)"
        }
    }

    override fun toString(): String {
        return "StaffModel(userSession=$userSession, userInformation=$userInformation, status=$status)"
    }
}