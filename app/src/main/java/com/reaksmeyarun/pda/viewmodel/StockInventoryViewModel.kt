package com.reaksmeyarun.pda.viewmodel

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.ig.iginnovation.superapp.driver.mycustomclass.MyMutableLiveData
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.CategoryAdapter
import com.reaksmeyarun.pda.adapter.ItemAdapter
import com.reaksmeyarun.pda.datamodel.StockInventoryDataModel
import com.reaksmeyarun.pda.listener.OnClickListener
import com.reaksmeyarun.pda.model.CategoryModel
import com.reaksmeyarun.pda.model.ItemModel
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.view.activity.S0100StockInventoryActivity
import kotlinx.android.synthetic.main.activity_s0100_stock_inventory.*
import kotlinx.android.synthetic.main.s0120_stock_inventory_stock_item_layout.*
import kotlinx.android.synthetic.main.s0220_stock_inventory_stock_category_layout.*
import kotlin.collections.ArrayList

class StockInventoryViewModel(var stockInventoryDataModel: StockInventoryDataModel, var activity : S0100StockInventoryActivity) : ViewModel() {
    private val TAG = "StockInventoryViewModel"
    var stockInventoryDM = MyMutableLiveData<StockInventoryDataModel>()
    init {
        stockInventoryDM.setValue(stockInventoryDataModel)
        stockInventoryDataModel.state = StockInventoryDataModel.STOCK_S0120
    }
    fun handlePressBack(view: View){
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
    var optionList = ArrayList<String>()
    fun bindingSpinnerS0120(){
        optionList.add("Search")
        optionList.add("Choose date")
        activity.searchLayout.visibility = View.GONE
        activity.startDateEndDateLayout.visibility = View.GONE
        val adapter = ArrayAdapter(activity, android.R.layout.simple_dropdown_item_1line, optionList)
        activity.spinnerOption.adapter = adapter
        activity.spinnerOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0 ->{
                        activity.progressing.visibility = View.VISIBLE
                        android.os.Handler().postDelayed({
                            activity.progressing.visibility = View.GONE
                            activity.searchLayout.visibility = View.VISIBLE
                            activity.startDateEndDateLayout.visibility = View.GONE
                        },100)
                    }
                    1 ->{
                        activity.progressing.visibility = View.VISIBLE
                        android.os.Handler().postDelayed({
                            activity.progressing.visibility = View.GONE
                            activity.searchLayout.visibility = View.GONE
                            activity.startDateEndDateLayout.visibility = View.VISIBLE
                        },100)
                    }
                }
            }
        }
    }
    var itemList = ArrayList<ItemModel>()
    fun bindingItemS0120(){
        for(value in 1..10){
            itemList.add(ItemModel(""))
        }
        var itemAdapter = ItemAdapter(activity, R.layout.item_stock_item_layout)
        activity.rvStockItem.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        activity.rvStockItem.adapter = itemAdapter
        itemAdapter.addItem(itemList)
        itemAdapter.onClickListener = object : OnClickListener{
            override fun onClick(position: Int) {
                activity.binding.progressing.visibility = View.VISIBLE
                android.os.Handler().postDelayed({
                    activity.binding.progressing.visibility = View.GONE
                    stockInventoryDataModel.state = StockInventoryDataModel.STOCK_S0130
                },300)
            }

        }
    }
    var categoryList = ArrayList<CategoryModel>()
    fun bindingCategoryS0220(){
        for(value in 1..10){
            categoryList.add(CategoryModel(""))
        }
        var categoryAdapter = CategoryAdapter(activity, R.layout.item_stock_category_layout)
        activity.rvStockCategory.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        activity.rvStockCategory.adapter = categoryAdapter
        categoryAdapter.addItem(categoryList)
    }
}