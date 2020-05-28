package com.reaksmeyarun.pda.model

data class InboxModel(
    var id : String = ""
//    var status : String ?= "",
//    var inboxInformation : InboxInformation
) {
    data class InboxInformation(
        var status : String = "",
        var title : String = "",
        var des : String = "",
        var timestamp : String = "" )
}