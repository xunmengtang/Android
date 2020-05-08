package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.ig.iginnovation.superapp.driver.mycustomclass.MyMutableLiveData
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.datamodel.StockInventoryDataModel
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.view.activity.S0100StockInventoryActivity

class StockInventoryViewModel(var stockInventoryDataModel: StockInventoryDataModel, var activity : S0100StockInventoryActivity) : ViewModel() {
    private val TAG = "StockInventoryViewModel"
    var stockInventoryDM = MyMutableLiveData<StockInventoryDataModel>()
    init {
        stockInventoryDM.setValue(stockInventoryDataModel)
        stockInventoryDataModel.state = StockInventoryDataModel.STOCK_S0110
    }
    fun handleBtnPressBack(view: View){
        activity.finish()
    }
    fun handleShowStockS0110(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            stockInventoryDataModel.state = StockInventoryDataModel.STOCK_S0110
        },300)
    }
    fun handleShowStockS0120(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            stockInventoryDataModel.state = StockInventoryDataModel.STOCK_S0120
        },300)
    }
    fun handleShowStockS0130(view: View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            stockInventoryDataModel.state = StockInventoryDataModel.STOCK_S0130
        },300)
    }
    fun handleShowStockS0140(view: View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            stockInventoryDataModel.state = StockInventoryDataModel.STOCK_S0140
        },300)
    }
    fun handleSearchItem(view: View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
        },300)
    }
    fun handleSearchCategory(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
        },300)
    }
    fun handleAddToItemStock(view: View){
        PopupMsg.alert(activity, activity.getString(R.string.msg_add), object : PopupMsg.OnClickButtonYesNoCallBack{
            override fun onYesCallBack() {
                android.os.Handler().postDelayed({
                    stockInventoryDataModel.state = StockInventoryDataModel.STOCK_S0120
                },300)
            }

            override fun onNoCallBack() {
                activity.binding.progressing.visibility = View.GONE
            }

        })
    }
    fun handleAddToCategoryStock(view: View){
        PopupMsg.alert(activity, activity.getString(R.string.msg_add), object : PopupMsg.OnClickButtonYesNoCallBack{
            override fun onYesCallBack() {
                android.os.Handler().postDelayed({
                    stockInventoryDataModel.state = StockInventoryDataModel.STOCK_S0220
                },300)
            }

            override fun onNoCallBack() {
                activity.binding.progressing.visibility = View.GONE
            }

        })
    }
    fun handleShowStockS0220(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            stockInventoryDataModel.state = StockInventoryDataModel.STOCK_S0220
        },300)
    }
    fun handleShowStockS0230(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            stockInventoryDataModel.state = StockInventoryDataModel.STOCK_S0230
        },300)
    }
}