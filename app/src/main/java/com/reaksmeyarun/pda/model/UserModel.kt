package com.reaksmeyarun.pda.model

import com.reaksmeyarun.pda.constance.AppConstance

class UserModel{
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
        var gender : String ?= ""
        var birthOfDate : String ?= ""
        var birthOfPlace : String ?= ""
        var phoneNumber : String ?= ""
        var emailAddress : String ?= ""
        var address : String ?= ""
        var rule = Rule()
        override fun toString(): String {
            return "UserInformation(id=$id, icon=$icon, firstName=$firstName, lastName=$lastName, gender=$gender, birthOfDate=$birthOfDate, birthOfPlace=$birthOfPlace, phoneNumber=$phoneNumber, emailAddress=$emailAddress, address=$address, rule=$rule)"
        }
    }

    class Rule{
        var rule : String ?= ""
        var title : String ?= ""
        override fun toString(): String {
            return "Rule(rule=$rule, title=$title)"
        }
    }

    override fun toString(): String {
        return "UserModel(userSession=$userSession, userInformation=$userInformation, status=$status)"
    }

}