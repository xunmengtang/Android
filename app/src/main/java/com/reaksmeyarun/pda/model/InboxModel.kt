package com.reaksmeyarun.pda.model

class InboxModel(
    var id : String = ""
//    ,    var inbox : Inbox
) {
    class Inbox(
        var status : String = "",
        var title : String = "",
        var des : String = "",
        var timestamp : String = "" )
}