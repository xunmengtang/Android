package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.*
import com.reaksmeyarun.pda.customView.BubbleTabBar.listener.OnBubbleClickListener
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.HomeDataModel
import com.reaksmeyarun.pda.model.*
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import kotlinx.android.synthetic.main.activity_p0200_home.*
import kotlinx.android.synthetic.main.activity_p0200_home.toolbar


@Suppress("DEPRECATION")
class HomeViewModel(var homeDataModel : HomeDataModel, var activity : P0200HomeActivity) : ViewModel() {

    private val TAG = "HomeViewModel"
    var homeData = MyMutableLiveData<HomeDataModel>()
    var statusCart : Int = 1
//  array
    var itemStockModel = ArrayList<StockItemModel>()
    var itemModelList = ArrayList<ItemModel>()
    var categoryModelList = ArrayList<CategoryModel>()
    init {
        homeData.setValue(homeDataModel)
        homeDataModel.state = HomeDataModel.HOME_P0210
    }
    fun handleBubbleTabBar(){
        activity.bubbleTabBar.addBubbleListener(object : OnBubbleClickListener {
            override fun onBubbleClick(id: Int) {
                when (id) {
                    R.id.home -> {
                        homeDataModel.state = HomeDataModel.HOME_P0210
                        activity.toolbar.visibility = View.VISIBLE
                    }
                    R.id.cart -> {
                        homeDataModel.state = HomeDataModel.HOME_P0240
                        activity.toolbar.visibility = View.GONE
                    }
                }
            }
        })
    }
}