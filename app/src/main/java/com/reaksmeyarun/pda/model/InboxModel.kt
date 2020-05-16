package com.reaksmeyarun.pda.model

class InboxModel(
    var id : String = ""
//    var status : String ?= "",
//    var inboxInformation : InboxInformation
) {
    class InboxInformation(
        var status : String = "",
        var title : String = "",
        var des : String = "",
        var timestamp : String = "" )
}