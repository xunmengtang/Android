package com.reaksmeyarun.pda.datamodel

import com.ig.iginnovation.superapp.driver.baseclass.BaseDataModel

class StockInventoryDataModel : BaseDataModel() {
    companion object{
        const val STOCK_S0110 = 1
        const val STOCK_S0120 = 2
        const val STOCK_S0130 = 3
        const val STOCK_S0140 = 4
        const val STOCK_S0220 = 10
        const val STOCK_S0230 = 20
        const val STOCK_S0240 = 30
    }

    var state : Int ?= STOCK_S0110
    set(value) {
        field = value
        when(state){
            STOCK_S0110 ->{
                showStockS0110 = true
                showStockS0120 = false
                showStockS0130 = false
                showStockS0140 = false
                showStockS0220 = false
                showStockS0230 = false
                showStockS0240 = false
            }
            STOCK_S0120 ->{
                showStockS0110 = false
                showStockS0120 = true
                showStockS0130 = false
                showStockS0140 = false
                showStockS0220 = false
                showStockS0230 = false
                showStockS0240 = false
            }
            STOCK_S0130 ->{
                showStockS0110 = false
                showStockS0120 = false
                showStockS0130 = true
                showStockS0140 = false
                showStockS0220 = false
                showStockS0240 = false
            }
            STOCK_S0140 ->{
                showStockS0110 = false
                showStockS0120 = false
                showStockS0130 = false
                showStockS0140 = true
                showStockS0220 = false
                showStockS0230 = false
                showStockS0240 = false
            }
            STOCK_S0220 ->{
                showStockS0110 = false
                showStockS0120 = false
                showStockS0130 = false
                showStockS0140 = false
                showStockS0220 = true
                showStockS0230 = false
                showStockS0240 = false
            }
            STOCK_S0230 ->{
                showStockS0110 = false
                showStockS0120 = false
                showStockS0130 = false
                showStockS0140 = false
                showStockS0220 = false
                showStockS0230 = true
                showStockS0240 = false
            }
            STOCK_S0240 ->{
                showStockS0110 = false
                showStockS0120 = false
                showStockS0130 = false
                showStockS0140 = false
                showStockS0220 = false
                showStockS0230 = false
                showStockS0240 = true
            }
        }
        propertiesChangedCallback.onChanged()
    }
    var showStockS0110 : Boolean ?= true
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showStockS0120 : Boolean ?= false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showStockS0130 : Boolean ?= false
    set(value){
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showStockS0140 : Boolean ?= false
    set(value){
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showStockS0220 : Boolean ?= false
    set(value){
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showStockS0230 : Boolean ?= false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showStockS0240 : Boolean ?= false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
}