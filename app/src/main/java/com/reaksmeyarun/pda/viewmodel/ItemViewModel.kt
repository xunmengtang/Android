package com.reaksmeyarun.pda.viewmodel

import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.CategoryAdapter
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseAdapter.Companion.STATUS_ONCLICKLISTENER
import com.reaksmeyarun.pda.connection.FirebaseConnection.Companion.databaseReference
import com.reaksmeyarun.pda.connection.FirebaseConnection.Companion.firebaseAuth
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.constance.AppConstance.Companion.CATEGORY_INFORMATION_NODE
import com.reaksmeyarun.pda.constance.AppConstance.Companion.CATEGORY_NODE
import com.reaksmeyarun.pda.constance.AppConstance.Companion.CLICK_LISTENER
import com.reaksmeyarun.pda.constance.AppConstance.Companion.DELETE_LISTENER
import com.reaksmeyarun.pda.constance.AppConstance.Companion.EXCEPTION
import com.reaksmeyarun.pda.constance.AppConstance.Companion.EXCEPTION_IN_INITIALIZER_ERROR
import com.reaksmeyarun.pda.constance.AppConstance.Companion.FINALLY
import com.reaksmeyarun.pda.constance.AppConstance.Companion.ITEM_INFORMATION_NODE
import com.reaksmeyarun.pda.constance.AppConstance.Companion.ITEM_NODE
import com.reaksmeyarun.pda.constance.AppConstance.Companion.NO_IMAGE_URL
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.ItemViewDataModel
import com.reaksmeyarun.pda.datamodel.ItemViewDataModel.Companion.ITEM_I0200
import com.reaksmeyarun.pda.datamodel.ItemViewDataModel.Companion.ITEM_I0210
import com.reaksmeyarun.pda.datamodel.ItemViewDataModel.Companion.ITEM_I0300
import com.reaksmeyarun.pda.datamodel.ItemViewDataModel.Companion.ITEM_I0310
import com.reaksmeyarun.pda.firebase.FirebaseEmit
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.listener.FirebaseGetChildListener
import com.reaksmeyarun.pda.listener.FirebaseGetListener
import com.reaksmeyarun.pda.listener.OnClickListener
import com.reaksmeyarun.pda.model.CategoryModel
import com.reaksmeyarun.pda.model.CategoryModel.CategoryInformation
import com.reaksmeyarun.pda.model.ItemModel
import com.reaksmeyarun.pda.model.ItemModel.*
import com.reaksmeyarun.pda.model.SettingModel
import com.reaksmeyarun.pda.preference.UserSession
import com.reaksmeyarun.pda.utils.DataSnapShotConvertUtils.dataSnapShotToArrayList
import com.reaksmeyarun.pda.utils.MapDataUtils.objectToMap
import com.reaksmeyarun.pda.utils.PopupMsg.OnClickButtonYesNoCallBack
import com.reaksmeyarun.pda.utils.PopupMsg.alert
import com.reaksmeyarun.pda.view.activity.I0100ItemActivity
import java.lang.System.currentTimeMillis

class ItemViewModel (var itemViewDataModel : ItemViewDataModel, var activity : I0100ItemActivity) : ViewModel() {
    val TAG = "ItemViewModel"
//    live data
    var itemData = MyMutableLiveData<ItemViewDataModel>()
//    adapter
//    val categoryAdapter = CategoryAdapter(activity, R.layout.item_category_layout)
//    val categoryItemAdapter = SettingAdapter(activity, R.layout.item_setting_layout)
//    val itemAdapter = ItemAdapter(activity, R.layout.item_item_layout)
//    val itemInformationAdapter = ItemInformationAdapter(activity, R.layout.item_item_layout)
    val spinnerAdapter = ArrayAdapter(activity, android.R.layout.simple_dropdown_item_1line, arrayListOf<String>())

    //    arrayList
    private var data = ArrayList<ItemInformation>()
    var categoryModelListID = ArrayList<String>()
    var categoryModelListName = ArrayList<String>()
    var categoryModelListCategoryInformation = ArrayList<CategoryInformation>()
    var categoryItemInformation = ArrayList<ItemInformation>()
    var categoryItemInformationID = ArrayList<String>()
    var categoryModelList = ArrayList<CategoryModel>()
    var itemModelListID = ArrayList<String>()
    var itemList = ArrayList<ItemModel>()
    var itemListItemInformation = ArrayList<ItemInformation>()
//  listener
    var categoryOnClickListenerStatus : Int = 0
    var itemOnClickListenerStatus : Int = 0
//  selection
    var spinnerDefaultSelected : Int = 0
    var spinnerIndexSelected : Int = 0
//  status action
    var categorySize = 0
    var statusPush = false
    var statusCategoryPost = true
//  object
    var onEditCategoryInformation = CategoryInformation()
    var category = CategoryModel()
    var item = ItemModel()
    var oldItem = ItemModel()
    var newItem = ItemModel()
//  firebase
    var firebaseEmit = FirebaseEmit()
    init {
        itemData.setValue(itemViewDataModel)
        itemViewDataModel.state = ItemViewDataModel.ITEM_I0110
    }

    fun handleShowI0110(view : View){
        itemViewDataModel.state = ItemViewDataModel.ITEM_I0110
    }

    fun handleShowI0200(view : View){
        itemViewDataModel.state = ITEM_I0200
    }

    fun handleShowI0210(view : View){
        itemViewDataModel.state = ItemViewDataModel.ITEM_I0210
    }

    fun handleShowI0300(view : View){
        itemViewDataModel.state = ITEM_I0300
    }

    fun handleShowI0310(view : View){
        itemViewDataModel.state = ITEM_I0310
    }

    private var categoryItemList = ArrayList<SettingModel>()

}