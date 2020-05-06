package com.reaksmeyarun.pda.datamodel

import android.util.Log
import com.ig.iginnovation.superapp.driver.baseclass.BaseDataModel

class HomeDataModel : BaseDataModel(){
    companion object{
        const val HOME_SCREEN = 1
        const val SEARCH_SCREEN = 2
        const val QUANTITIES_SCREEN = 3
        const val CART_SCREEN = 4
        const val PAYMENT_SCREEN = 5
        const val CONFIRM_SCREEN = 6
    }

    var state : Int? = HOME_SCREEN
    set(value) {
        field = value
        when(state){
            HOME_SCREEN ->{
                showHomeScreen = true
                showSearchScreen = false
                showQuantitiesScreen = false
                showCartScreen = false
                showPaymentScreen = false
                showConfirmScreen = false
            }
            SEARCH_SCREEN ->{
                showHomeScreen = false
                showSearchScreen = true
                showQuantitiesScreen = false
                showCartScreen = false
                showPaymentScreen = false
                showConfirmScreen = false
            }
            QUANTITIES_SCREEN ->{
                showHomeScreen = false
                showSearchScreen = false
                showQuantitiesScreen = true
                showCartScreen = false
                showPaymentScreen = false
                showConfirmScreen = false
            }
            CART_SCREEN ->{
                showHomeScreen = false
                showSearchScreen = false
                showQuantitiesScreen = false
                showCartScreen = true
                showPaymentScreen = false
                showConfirmScreen = false
            }
            PAYMENT_SCREEN ->{
                showHomeScreen = false
                showSearchScreen = false
                showQuantitiesScreen = false
                showCartScreen = false
                showPaymentScreen = true
                showConfirmScreen = false
            }
            CONFIRM_SCREEN ->{
                showHomeScreen = false
                showSearchScreen = false
                showQuantitiesScreen = false
                showCartScreen = false
                showPaymentScreen = false
                showConfirmScreen = true
            }
        }
        propertiesChangedCallback.onChanged()
    }
    var showHomeScreen : Boolean? = true
    set(value){
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showSearchScreen : Boolean? = false
    set(value){
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showQuantitiesScreen : Boolean = false
    set(value){
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showCartScreen : Boolean = false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showPaymentScreen : Boolean = false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
    var showConfirmScreen : Boolean = false
    set(value) {
        field = value
        propertiesChangedCallback.onChanged()
    }
}