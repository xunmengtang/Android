package com.reaksmeyarun.pda.viewmodel

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.CartAdapter
import com.reaksmeyarun.pda.adapter.DiscountAdapter
import com.reaksmeyarun.pda.adapter.ItemAdapter
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.HomeDataModel
import com.reaksmeyarun.pda.listener.OnClickListener
import com.reaksmeyarun.pda.listener.OnDeleteListener
import com.reaksmeyarun.pda.model.CartModel
import com.reaksmeyarun.pda.model.CategoryModel
import com.reaksmeyarun.pda.model.DiscountModel
import com.reaksmeyarun.pda.model.ItemModel
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.view.activity.P0100SignInActivity
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import kotlinx.android.synthetic.main.activity_s0100_stock_inventory.*
import kotlinx.android.synthetic.main.p0210_home_main_layout.rvP0210
import kotlinx.android.synthetic.main.p0211_home_main_layout.*
import kotlinx.android.synthetic.main.p0220_home_item_layout.*
import kotlinx.android.synthetic.main.p0240_home_cart_item_layout.*
import kotlinx.android.synthetic.main.p0260_home_confirmation_layout.*


class HomeViewModel( var homeDataModel : HomeDataModel, var activity : P0200HomeActivity) : ViewModel() {
    private val TAG = "HomeViewModel"
    var homeData = MyMutableLiveData<HomeDataModel>()
    var statusCart : Int = 1

    init {
        homeData.setValue(homeDataModel)
        homeDataModel.state = HomeDataModel.HOME_P0210
        if(activity.rippleBackground.isRippleAnimationRunning)
            activity.rippleBackground.startRippleAnimation()
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
            activity.binding.toolbar.visibility = View.GONE
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
    fun handleBackPressS0230(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            if(statusCart == AppConstance.ADD_TO_CART){
                activity.binding.toolbar.visibility = View.VISIBLE
                homeDataModel.state = HomeDataModel.HOME_P0210
            }else if (statusCart == AppConstance.EDIT_ON_CART){
                homeDataModel.state = HomeDataModel.HOME_P0240
            }
        },300)
    }
    var itemList = ArrayList<ItemModel>()
    fun bindingItemP0210(){
        var itemAdapter = ItemAdapter(activity, R.layout.item_item_layout)
        activity.rvP0210.adapter = itemAdapter
        activity.rvP0210.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        itemAdapter.addItem(itemList)
        itemAdapter.onClickListener = object : OnClickListener{
            override fun onClick(position: Int) {
                activity.binding.progressing.visibility = View.VISIBLE
                android.os.Handler().postDelayed({
                    activity.binding.toolbar.visibility = View.GONE
                    activity.binding.progressing.visibility = View.GONE
                    homeDataModel.state = HomeDataModel.HOME_P0230
                    statusCart = AppConstance.ADD_TO_CART
                },300)
            }
        }
    }
    var searchItemList = ArrayList<ItemModel>()
    fun bindingItemP0230(){
        var searchItemAdapter = ItemAdapter(activity, R.layout.item_search_layout)
        activity.rvItemSearch.adapter = searchItemAdapter
        activity.rvItemSearch.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        searchItemAdapter.addItem(searchItemList)
    }
    var discountList = ArrayList<DiscountModel>()
    fun bindingDiscountP0210(){
        for(value in 1..5){
            discountList.add(DiscountModel(""))
        }
        var discountAdapter = DiscountAdapter(activity, R.layout.item_discount_layout)
        activity.rvP0210.adapter = discountAdapter
        activity.rvP0210.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        discountAdapter.addItem(discountList)
    }
    var cartList = ArrayList<CartModel>()
    fun bindingCartP0240(){
        for(value in 1..20){
            cartList.add(CartModel(""))
        }
        var cartAdapter = CartAdapter(activity, R.layout.item_cart_layout)
        activity.rvCart.adapter = cartAdapter
        activity.rvCart.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        cartAdapter.addItem(cartList)
        cartAdapter.onClickListener = object : OnClickListener{
            override fun onClick(position: Int) {
                activity.binding.progressing.visibility = View.VISIBLE
                android.os.Handler().postDelayed({
                    activity.binding.toolbar.visibility = View.GONE
                    activity.binding.progressing.visibility = View.GONE
                    homeDataModel.state = HomeDataModel.HOME_P0230
                    statusCart = AppConstance.EDIT_ON_CART
                },300)
            }
        }
        cartAdapter.onDeleteListener = object  : OnDeleteListener{
            override fun onDelete(position: Int) {
                PopupMsg.alert(activity, activity.getString(R.string.msg_delete),callBack = object :PopupMsg.OnClickButtonYesNoCallBack{
                    override fun onYesCallBack() {
                        activity.binding.progressing.visibility = View.VISIBLE
                        android.os.Handler().postDelayed({
                            activity.binding.progressing.visibility = View.GONE
                            Toast.makeText(activity, "Cart deleted", Toast.LENGTH_SHORT).show()
                        },300)
                    }

                    override fun onNoCallBack() {
//                        TO NOTHING
                    }

                })
            }
        }
    }
    fun handleBtnSearchItemClickP0230(view: View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
        },300)
    }
    var optionList = ArrayList<String>()
    fun bindingSpinnerS0210(){
        optionList.add("Item")
        optionList.add("Discount")
        val adapter = ArrayAdapter(activity, android.R.layout.simple_dropdown_item_1line, optionList)
        activity.spinner.adapter = adapter
        activity.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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
                            discountList.clear()
                            activity.progressing.visibility = View.GONE
                            bindingItemP0210()
                        },100)
                    }
                    1 ->{
                        activity.progressing.visibility = View.VISIBLE
                        android.os.Handler().postDelayed({
                            itemList.clear()
                            activity.progressing.visibility = View.GONE
                            bindingDiscountP0210()
                        },100)
                    }
                }
            }
        }
    }
    fun handleAddToCart(view : View){
        PopupMsg.alert(activity,activity.getString(R.string.msg_add), callBack = object :PopupMsg.OnClickButtonYesNoCallBack{
            override fun onYesCallBack() {
                activity.binding.progressing.visibility = View.VISIBLE
                android.os.Handler().postDelayed({
                    activity.binding.progressing.visibility = View.GONE
                    if(statusCart==AppConstance.ADD_TO_CART){
                        activity.binding.toolbar.visibility = View.VISIBLE
                        homeDataModel.state = HomeDataModel.HOME_P0210
                    }else if (statusCart==AppConstance.EDIT_ON_CART){
                        homeDataModel.state = HomeDataModel.HOME_P0240
                    }
                },300)
            }

            override fun onNoCallBack() {
            }
        })
    }
    fun handleSearchItem(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
        },300)
    }
    fun handleSignOut(){
        PopupMsg.alert(activity, activity.getString(R.string.msg_SignOut), object : PopupMsg.OnClickButtonYesNoCallBack{
            override fun onYesCallBack() {
                FirebaseConnection.firebaseAuth.signOut()
                activity.startActivity(Intent(activity, P0100SignInActivity::class.java))
                activity.finish()
            }
            override fun onNoCallBack() {
//              DO NOTHING
            }
        })
    }
}