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
        homeDataModel.state = HomeDataModel.HOME_SCREEN
    }
    fun handleHomeConfirmScreen (view: View){
        PopupMsg.alert(activity, activity.getString(R.string.msg_pay),object : PopupMsg.OnClickButtonYesNoCallBack{
            override fun onYesCallBack() {
                activity.binding.progressing.visibility = View.VISIBLE
                android.os.Handler().postDelayed({
                    activity.binding.progressing.visibility = View.GONE
                    homeDataModel.state = HomeDataModel.CONFIRM_SCREEN
                },300)
            }

            override fun onNoCallBack() {
            }

        })
    }
    fun handleHomePayment(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            homeDataModel.state = HomeDataModel.PAYMENT_SCREEN
        },300)
    }
    fun handleHomeCart(view: View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            homeDataModel.state = HomeDataModel.CART_SCREEN
        },300)
    }
    fun handleHomeMain(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            activity.binding.toolbar.visibility = View.VISIBLE
            homeDataModel.state = HomeDataModel.HOME_SCREEN
        },300)
    }
    fun handleGotoTransaction(view: View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            activity.binding.toolbar.visibility = View.GONE
            homeDataModel.state = HomeDataModel.SEARCH_SCREEN
        },300)
    }
    fun handleGotoQuantities(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            homeDataModel.state = HomeDataModel.QUANTITIES_SCREEN
        },300)
    }
    fun bindingCategory(){
        for(value in 1..10){
            categoryList.add(CategoryModel(""))
        }
        var categoryAdapter = CategoryAdapter(activity, R.layout.item_category_layout)
        activity.rvCategory.adapter = categoryAdapter
        activity.rvCategory.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter.addItem(categoryList)
    }
    var itemList = ArrayList<ItemModel>()
    fun bindingItem(){
        for(value in 1..10){
            itemList.add(ItemModel(""))
        }
        var itemAdapter = ItemAdapter(activity, R.layout.item_item_layout)
        activity.rvItem.adapter = itemAdapter
        activity.rvItem.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        itemAdapter.addItem(itemList)
    }
    var searchItemList = ArrayList<ItemModel>()
    fun bindingSearchItem(){
        for(value in 1..10){
            searchItemList.add(ItemModel(""))
        }
        var searchItemAdapter = ItemAdapter(activity, R.layout.item_search_layout)
        activity.rvItemSearch.adapter = searchItemAdapter
        activity.rvItemSearch.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        searchItemAdapter.addItem(searchItemList)
    }
    var cartList = ArrayList<CartModel>()
    fun bindingCart(){
        for(value in 1..10){
            cartList.add(CartModel(""))
        }
        var cartAdapter = CartAdapter(activity, R.layout.item_cart)
        activity.rvCart.adapter = cartAdapter
        activity.rvCart.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        cartAdapter.addItem(cartList)
    }
}