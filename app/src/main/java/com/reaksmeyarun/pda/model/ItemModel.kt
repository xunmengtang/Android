package com.reaksmeyarun.pda.model

class ItemModel(
    var userSession : UserModel.UserSession ? = null,
    var itemInformation : ItemInformationModel ? = null,
    var categoryInformation : CategoryInformationModel ? = null,
    var timestamp : String ?= "")