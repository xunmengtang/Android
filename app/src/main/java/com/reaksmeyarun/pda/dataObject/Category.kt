package com.reaksmeyarun.pda.dataObject

import com.reaksmeyarun.pda.firebaseRepo.RequestCategory.*

object Category {
    var category = ArrayList<ResponseCategory>()
    var clothes = ArrayList<ResponseCategory>()
    var watches = ArrayList<ResponseCategory>()
    var shoes = ArrayList<ResponseCategory>()
    var pants = ArrayList<ResponseCategory>()
    fun resetValue(){
        category = ArrayList<ResponseCategory>()
        clothes = ArrayList<ResponseCategory>()
        watches = ArrayList<ResponseCategory>()
        shoes = ArrayList<ResponseCategory>()
        pants = ArrayList<ResponseCategory>()
    }
}