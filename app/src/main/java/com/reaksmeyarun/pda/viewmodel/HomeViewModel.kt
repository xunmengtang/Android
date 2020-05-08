package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.ig.iginnovation.superapp.driver.mycustomclass.MyMutableLiveData
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.CartAdapter
import com.reaksmeyarun.pda.adapter.CategoryAdapter
import com.reaksmeyarun.pda.adapter.ItemAdapter
import com.reaksmeyarun.pda.datamodel.HomeDataModel
import com.reaksmeyarun.pda.model.CartModel
import com.reaksmeyarun.pda.model.CategoryModel
import com.reaksmeyarun.pda.model.ItemModel
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import kotlinx.android.synthetic.main.p0210_home_main_layout.*
import kotlinx.android.synthetic.main.p0220_home_item_layout.*
import kotlinx.android.synthetic.main.p0240_home_cart_item_layout.*

class HomeViewModel( var homeDataModel : HomeDataModel, var activity : P0200HomeActivity) : ViewModel() {
    private val TAG = "HomeViewModel"
    private var categoryList = ArrayList<CategoryModel>()
    var homeData = MyMutableLiveData<HomeDataModel>()

    init {
        homeData.setValue(homeDataModel)
        homeDataModel.state = HomeDataModel.HOME_P0210
    }
    fun handleHomeP0260 (view: View){
        PopupMsg.alert(activity, activity.getString(R.string.msg_pay),object : PopupMsg.OnClickButtonYesNoCallBack{
            override fun onYesCallBack() {
                activity.binding.progressing.visibility = View.VISIBLE
                android.os.Handler().postDelayed({
                    activity.binding.progressing.visibility = View.GONE
                    homeDataModel.state = HomeDataModel.HOME_P0260
                },300)
            }

            override fun onNoCallBack() {
            }

        })
    }
    fun handleHomeP0250(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            homeDataModel.state = HomeDataModel.HOME_P0250
        },300)
    }
    fun handleHomeP0240(view: View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            homeDataModel.state = HomeDataModel.HOME_P0240
        },300)
    }
    fun handleHomeP0210(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            activity.binding.toolbar.visibility = View.VISIBLE
            homeDataModel.state = HomeDataModel.HOME_P0210
        },300)
    }
    fun handleHomeP0220(view: View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            activity.binding.toolbar.visibility = View.GONE
            homeDataModel.state = HomeDataModel.HOME_P0220
        },300)
    }
    fun handleHomeP0230(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            homeDataModel.state = HomeDataModel.HOME_P0230
        },300)
    }
    fun bindingCategoryP0210(){
        for(value in 1..20){
            categoryList.add(CategoryModel(""))
        }
        var categoryAdapter = CategoryAdapter(activity, R.layout.item_category_layout)
        activity.rvCategory.adapter = categoryAdapter
        activity.rvCategory.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter.addItem(categoryList)
    }
    var itemList = ArrayList<ItemModel>()
    fun bindingItemP0210(){
        for(value in 1..20){
            itemList.add(ItemModel(""))
        }
        var itemAdapter = ItemAdapter(activity, R.layout.item_item_layout)
        activity.rvItem.adapter = itemAdapter
        activity.rvItem.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        itemAdapter.addItem(itemList)
    }
    var searchItemList = ArrayList<ItemModel>()
    fun bindingItemP0230(){
        for(value in 1..20){
            searchItemList.add(ItemModel(""))
        }
        var searchItemAdapter = ItemAdapter(activity, R.layout.item_search_layout)
        activity.rvItemSearch.adapter = searchItemAdapter
        activity.rvItemSearch.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        searchItemAdapter.addItem(searchItemList)
    }
    var cartList = ArrayList<CartModel>()
    fun bindingCartP0240(){
        for(value in 1..20){
            cartList.add(CartModel(""))
        }
        var cartAdapter = CartAdapter(activity, R.layout.item_cart)
        activity.rvCart.adapter = cartAdapter
        activity.rvCart.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        cartAdapter.addItem(cartList)
    }
    fun handleBtnSearchItemClickP0230(view: View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
        },300)
    }
}