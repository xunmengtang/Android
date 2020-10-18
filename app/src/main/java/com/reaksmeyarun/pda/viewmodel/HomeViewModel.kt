package com.reaksmeyarun.pda.viewmodel

import android.content.Intent
import android.opengl.Visibility
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.R.id.*
import com.reaksmeyarun.pda.adapter.HomeCategoryAdapter
import com.reaksmeyarun.pda.adapter.HomeItemAdapter
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.HomeDataModel
import com.reaksmeyarun.pda.listener.RVItemClickCallback
import com.reaksmeyarun.pda.model.*
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.view.activity.C0100CategoryActivity
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import com.reaksmeyarun.pda.view.activity.Z0200SignInActivity
import kotlinx.android.synthetic.main.activity_p0200_home.*

class HomeViewModel(var homeDataModel : HomeDataModel, var activity : P0200HomeActivity) : ViewModel() {

    private val TAG = "HomeViewModel"
    var homeData = MyMutableLiveData<HomeDataModel>()
//  array
    var itemModelList = ArrayList<ItemModel>()
    var categoryModelList = ArrayList<CategoryModel>()
    private val homeCategoryAdapter = HomeCategoryAdapter(activity, R.layout.category_item_layout)
    init {
        homeData.setValue(homeDataModel)
        showHomeScreen()
    }

    fun showHomeScreen(){
        homeDataModel.state = HomeDataModel.HOME_P0210
    }
    fun handleBubbleTabBar(){
//        activity.bubbleTabBar.addBubbleListener(object : OnBubbleClickListener {
//            override fun onBubbleClick(id: Int) {
//                when (id) {
//                    R.id.home -> {
//                        homeDataModel.state = HomeDataModel.HOME_P0210
//                        activity.toolbar.visibility = View.VISIBLE
//                    }
//                    R.id.cart -> {
//                        homeDataModel.state = HomeDataModel.HOME_P0240
//                        activity.toolbar.visibility = View.GONE
//                    }
//                }
//            }
//        })
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
            nav_signOut ->  FirebaseAuth.getInstance().signOut().run { activity.startActivity(activity, Z0200SignInActivity::class.java) }
        }
    }
    private var categoryList = ArrayList<CategoryModel>()
    fun initCategory(){
        categoryList.add(CategoryModel(UserModel.UserSession(), CategoryInformationModel(categoryName = "Breakfast")))
        categoryList.add(CategoryModel(UserModel.UserSession(), CategoryInformationModel(categoryName = "Launch")))
        categoryList.add(CategoryModel(UserModel.UserSession(), CategoryInformationModel(categoryName = "Dinner")))
        categoryList.add(CategoryModel(UserModel.UserSession(), CategoryInformationModel(categoryName = "Food")))
        categoryList.add(CategoryModel(UserModel.UserSession(), CategoryInformationModel(categoryName = "Snack")))
        categoryList.add(CategoryModel(UserModel.UserSession(), CategoryInformationModel(categoryName = "Ice-Cream")))
        categoryList.add(CategoryModel(UserModel.UserSession(), CategoryInformationModel(categoryName = "Fresh Juice")))
        categoryList.add(CategoryModel(UserModel.UserSession(), CategoryInformationModel(categoryName = "Cafe")))
        activity.rvCategory.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        activity.rvCategory.adapter = homeCategoryAdapter
        homeCategoryAdapter.addItem(categoryList)
        homeCategoryAdapter.enableAnimation = false
        homeCategoryAdapter.setItemClickCallBack(object : RVItemClickCallback<CategoryModel>{
            override fun onClick(item: CategoryModel, pos: Int) {
                activity.binding.categoryName.setTextColor(ContextCompat.getColor(activity, R.color.hintTextColor))
                activity.binding.dot.visibility = View.GONE
                activity.binding.rvCategory.scrollToPosition(pos)
            }
        })
    }
    private var itemList = ArrayList<ItemModel>()
    fun initItem(){
        itemList.add(ItemModel(UserModel.UserSession(),ItemInformationModel(itemName = "Sandwich")))
        itemList.add(ItemModel(UserModel.UserSession(),ItemInformationModel(itemName = "Burger")))
        itemList.add(ItemModel(UserModel.UserSession(),ItemInformationModel(itemName = "Pizza")))
        val homeItemAdapter = HomeItemAdapter(activity, R.layout.items_item_layout)
        activity.rvItem.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        activity.rvItem.adapter = homeItemAdapter
        homeItemAdapter.addItem(itemList)
    }

    fun handleAllCategory(view : View){
        activity.binding.categoryName.setTextColor(ContextCompat.getColor(activity, R.color.black))
        activity.binding.dot.visibility = View.VISIBLE
        homeCategoryAdapter.selectedItem(null)
    }
}