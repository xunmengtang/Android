package com.reaksmeyarun.pda.viewmodel

import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModel
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.customView.BubbleTabBar.listener.OnBubbleClickListener
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.HomeDataModel
import com.reaksmeyarun.pda.model.*
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.view.activity.A0200UserActivity
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import kotlinx.android.synthetic.main.activity_p0200_home.*
import kotlinx.android.synthetic.main.activity_p0200_home.toolbar

class HomeViewModel(var homeDataModel : HomeDataModel, var activity : P0200HomeActivity) : ViewModel() {

    private val TAG = "HomeViewModel"
    var homeData = MyMutableLiveData<HomeDataModel>()
//  array
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
    fun handleBackPress(){
        when {
            activity.drawer_layout.isDrawerOpen(GravityCompat.START) -> activity.drawer_layout.closeDrawer(GravityCompat.START)
            else -> PopupMsg.alert(activity, activity.getString(R.string.msg_close),
                object : PopupMsg.OnClickButtonYesNoCallBack {
                    override fun onYesCallBack() {
                        activity.finish()
                    }
                    override fun onNoCallBack() {/* Do nothing*/}
                })
        }
    }
    fun handleDrawerLayout(view : View){
        activity.drawer_layout.openDrawer(GravityCompat.START)
    }
    fun handleUserInformation(){

    }
    fun handleNavigationItemSelected(item : MenuItem){
        when(item.itemId){
            R.id.nav_user->{
                activity.startActivity(activity, A0200UserActivity::class.java)
            }
        }
    }
}