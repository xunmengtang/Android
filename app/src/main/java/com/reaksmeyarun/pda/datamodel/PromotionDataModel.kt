package com.reaksmeyarun.pda.datamodel

import com.ig.iginnovation.superapp.driver.baseclass.BaseDataModel

class PromotionDataModel : BaseDataModel() {
    companion object{
        const val PROMOTION_P0410 = 1
        const val PROMOTION_P0420 = 2
    }
    var state : Int ?= PROMOTION_P0410
    set(value) {
        field = value
        when(state){
            PROMOTION_P0410 ->{
                showPromotionP0410 = true
                showPromotionP0420 = false
            }
            PROMOTION_P0420 ->{
                showPromotionP0410 = false
                showPromotionP0420 = true
            }
        }
        propertiesChangedCallback.onChanged()
    }

    var showPromotionP0410 : Boolean ?= true
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showPromotionP0420 : Boolean ?= false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
}