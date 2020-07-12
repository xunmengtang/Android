package com.reaksmeyarun.pda.viewmodel

import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModel
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.R.id.*
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.connection.FirebaseConnection.Companion.InstanceAuth
import com.reaksmeyarun.pda.constance.KeyConstance
import com.reaksmeyarun.pda.customView.BubbleTabBar.listener.OnBubbleClickListener
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.HomeDataModel
import com.reaksmeyarun.pda.model.*
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.view.activity.C0100CategoryActivity
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import com.reaksmeyarun.pda.view.activity.Z0200SignInActivity
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
        showHomeScreen()
    }

    fun showHomeScreen(){
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
//        TODO :
    }
    fun handleNavigationItemSelected(item : MenuItem){
        when(item.itemId){
            nav_home -> showHomeScreen()
            nav_category -> activity.startActivity(Intent(activity, C0100CategoryActivity::class.java))
            nav_signOut -> InstanceAuth.signOut().run { activity.startActivity(activity, Z0200SignInActivity::class.java) }
        }
    }
}