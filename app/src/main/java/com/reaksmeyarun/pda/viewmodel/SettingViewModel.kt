package com.reaksmeyarun.pda.viewmodel

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.CategoryAdapter
import com.reaksmeyarun.pda.adapter.ItemAdapter
import com.reaksmeyarun.pda.adapter.SettingAdapter
import com.reaksmeyarun.pda.firebase.FirebaseEmit
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.connection.FirebaseConnection.Companion.databaseReference
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.datamodel.SettingDataModel
import com.reaksmeyarun.pda.listener.FirebaseGetListener
import com.reaksmeyarun.pda.listener.OnClickListener
import com.reaksmeyarun.pda.model.CategoryModel
import com.reaksmeyarun.pda.model.CategoryModel.*
import com.reaksmeyarun.pda.model.ItemModel
import com.reaksmeyarun.pda.model.SettingModel
import com.reaksmeyarun.pda.model.UserModel
import com.reaksmeyarun.pda.utils.DataSnapShotConvert
import com.reaksmeyarun.pda.utils.MapData
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.view.activity.Z0200SettingActivity
import kotlinx.android.synthetic.main.p0211_home_main_layout.spinner
import kotlinx.android.synthetic.main.z0210_setting_layout.*
import kotlinx.android.synthetic.main.z0220_category_item_layout.*
import kotlinx.android.synthetic.main.z0230_category_layout.*
import kotlinx.android.synthetic.main.z0231_category_add_edit_layout.*
import kotlinx.android.synthetic.main.z0240_item_layout.*
import kotlinx.android.synthetic.main.z0241_item_add_edit_layout.*
import kotlin.collections.ArrayList

class SettingViewModel(var settingDataModel: SettingDataModel, var activity : Z0200SettingActivity) : ViewModel() {
    private val TAG = "SettingViewModel"
    var settingDM = MyMutableLiveData<SettingDataModel>()
    init {
        settingDM.setValue(settingDataModel)
        settingDataModel.state = SettingDataModel.SETTING_Z0210
    }
    fun handlePressBack(view : View){
        activity.finish()
    }

    fun handleShowZ0210(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            settingDataModel.state = SettingDataModel.SETTING_Z0210
        },300)
    }
    fun handleShowZ0220(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            settingDataModel.state = SettingDataModel.SETTING_Z0220
        },300)
    }
    fun handleShowZ0230(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            settingDataModel.state = SettingDataModel.SETTING_Z0220
        },300)
    }
    fun handleShowZ0231(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            settingDataModel.state = SettingDataModel.SETTING_Z0231
        },300)
    }
    fun handleShowZ0240(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            settingDataModel.state = SettingDataModel.SETTING_Z0240
        },300)
    }
    fun handleShowZ0241(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            settingDataModel.state = SettingDataModel.SETTING_Z0241
        },300)
    }
    var settingList = ArrayList<SettingModel>()
    fun bindingRvZ0220(){
        settingList.add(SettingModel("",activity.getString(R.string.categoryLabel),R.drawable.ic_category))
        settingList.add(SettingModel("",activity.getString(R.string.itemLabel),R.drawable.ic_item))
        var settingAdapter = SettingAdapter(activity, R.layout.item_setting_layout)
        activity.rvZ0220Setting.adapter = settingAdapter
        activity.rvZ0220Setting.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        settingAdapter.addItem(settingList)
        settingAdapter.onClickListener = object : OnClickListener{
            override fun onClick(position: Int) {
                activity.binding.progressing.visibility = View.VISIBLE
                android.os.Handler().postDelayed({
                    activity.binding.progressing.visibility = View.GONE
                    if(position==0)
                        settingDataModel.state = SettingDataModel.SETTING_Z0230
                    else if (position == 1)
                        settingDataModel.state = SettingDataModel.SETTING_Z0240
                },300)
            }

        }
    }
    var categoryItemList = ArrayList<SettingModel>()
    fun bindingRvZ0210(){
        categoryItemList.add(SettingModel("","Language",R.drawable.ic_language))
        categoryItemList.add(SettingModel("","Category & Item",R.drawable.ic_stock))
        var categoryItemAdapter = SettingAdapter(activity, R.layout.item_setting_layout)
        activity.rvZ0210setting.adapter = categoryItemAdapter
        activity.rvZ0210setting.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        categoryItemAdapter.addItem(categoryItemList)
        categoryItemAdapter.onClickListener = object : OnClickListener{
            override fun onClick(position: Int) {
                activity.binding.progressing.visibility = View.VISIBLE
                android.os.Handler().postDelayed({
                    activity.binding.progressing.visibility = View.GONE
                    if(position == 0)
                        Toast.makeText(activity,"Language",Toast.LENGTH_SHORT).show()
                    else if (position == 1)
                        settingDataModel.state = SettingDataModel.SETTING_Z0220
                },300)
            }
        }
    }
    var categoryZ0230 = ArrayList<CategoryInformation>()
    fun bindingRvZ0230(){
        var emit = FirebaseEmit()
        emit.gets(TAG, databaseReference(AppConstance.CATEGORY_NODE))
        emit.firebaseGetListener = object : FirebaseGetListener{
            override fun onCompleteListener(dataSnapshot: DataSnapshot) {
//                categoryZ0230.addAll(DataSnapShotConvert().dataSnapShotToArrayList(CategoryModel.CategoryInformation().javaClass, dataSnapshot))
                categoryZ0230.addAll(DataSnapShotConvert.dataSnapShotToArrayList(CategoryInformation().javaClass, dataSnapshot))
                if(categoryZ0230.size == 0) {
//                    TODO :
                    activity.empty_layout_Z0240.visibility = View.VISIBLE
                    activity.rvZ0230.visibility = View.GONE
                }else{
                    activity.empty_layout_Z0240.visibility = View.GONE
                    activity.rvZ0230.visibility = View.VISIBLE
                    var categoryAdapter = CategoryAdapter(activity, R.layout.item_category_layout)
                    activity.rvZ0230.adapter = categoryAdapter
                    activity.rvZ0230.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    categoryAdapter.addItem(categoryZ0230)
                }
                Log.d(TAG, "${AppConstance.VIEW_MODEL} : ${AppConstance.CATEGORY_NODE} : $categoryList")
            }

            override fun onCancelListener(databaseError: DatabaseError) {
                PopupMsg.alert(activity, activity.getString(R.string.msg_something_wrong))
            }
        }
    }
    var itemZ0240 = ArrayList<ItemModel>()
    fun bindingRvZ0240(){
        var emit = FirebaseEmit()
        emit.get(TAG, databaseReference(AppConstance.ITEM_NODE))
        emit.firebaseGetListener = object : FirebaseGetListener{
            override fun onCompleteListener(dataSnapshot: DataSnapshot) {
//                itemZ0240 = DataSnapShotConvert().dataSnapShotToArrayList(ItemModel("", UserModel.UserSession(),
//                    ItemModel.ItemInformation(), CategoryModel.CategoryInformation()).javaClass, dataSnapshot)
                itemZ0240 = DataSnapShotConvert.dataSnapShotToArrayList(ItemModel("", UserModel.UserSession(),
                    ItemModel.ItemInformation(), CategoryInformation()
                ).javaClass, dataSnapshot)
                if(itemZ0240.size==0) {
//                  TODO :
                    activity.empty_layout_Z0240.visibility = View.VISIBLE
                    activity.rvZ0230.visibility = View.GONE
                }else{
                    activity.empty_layout_Z0240.visibility = View.GONE
                    activity.rvZ0230.visibility = View.VISIBLE
                    var itemAdapter = ItemAdapter(activity, R.layout.item_item_layout)
                    activity.rvZ0230.adapter = itemAdapter
                    activity.rvZ0230.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    itemAdapter.addItem(itemZ0240)
                }
            }

            override fun onCancelListener(databaseError: DatabaseError) {
                PopupMsg.alert(activity, activity.getString(R.string.msg_something_wrong))
            }
        }
    }
    var categoryList = ArrayList<CategoryModel>()
    var categoryInformationList  = ArrayList<CategoryInformation>()
    var spinnerSelected : Int = 0
    fun bindingSpinnerZ0241(){
//      Emit get categoryInformation from EmitGetChild
        var emit = FirebaseEmit()
        emit.get(TAG, databaseReference(AppConstance.CATEGORY_NODE))
        emit.firebaseGetListener = object : FirebaseGetListener {
            override fun onCompleteListener(dataSnapshot: DataSnapshot) {
//                categoryList.addAll(DataSnapShotConvert()
//                    .dataSnapShotToArrayList(CategoryModel("",UserModel.UserSession(), CategoryModel.CategoryInformation(), ItemModel.ItemInformation(), "").javaClass, dataSnapshot))
                if(categoryList.size == 0){
//                    TODO :
                }else{
                    categoryList.addAll(DataSnapShotConvert.dataSnapShotToArrayList(CategoryModel("",UserModel.UserSession(), CategoryInformation(), ItemModel.ItemInformation(), "").javaClass, dataSnapshot))
                }
            }

            override fun onCancelListener(databaseError: DatabaseError) {
                Log.e(TAG,"${AppConstance.DATABASE_ERROR} : $databaseError")
                PopupMsg.alert(activity, activity.getString(R.string.msg_something_wrong))
            }
        }
        if(categoryInformationList.size == 0)
            categoryInformationList.add(CategoryInformation("","No Category",""))
        val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_dropdown_item_1line, categoryInformationList)
        activity.spinner.adapter = arrayAdapter
        activity.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                activity.binding.progressing.visibility = View.VISIBLE
                android.os.Handler().postDelayed({
                    spinnerSelected = position
                    activity.binding.progressing.visibility = View.GONE
                },300)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                DO nothing
            }
        }
    }

    var category = CategoryModel("", UserModel.UserSession(), CategoryInformation(), ItemModel.ItemInformation(),"")
    fun handleCreateNewCategory(view : View){
        try{
            activity.binding.progressing.visibility =  View.VISIBLE

            category.id = databaseReference(AppConstance.CATEGORY_NODE).push().key
            category.userSession.id = FirebaseConnection.firebaseAuth.uid
            category.userSession.token = System.currentTimeMillis().toString()
            category.categoryInformation.id= databaseReference(AppConstance.CATEGORY_NODE).push().key
            category.categoryInformation.categoryName = activity.etCategoryName.text.toString().trim()
            category.categoryInformation.categoryDescription = activity.etCategoryDescription.text.toString()
            category.itemInformation = ItemModel.ItemInformation()
            category.timestamp = System.currentTimeMillis().toString()

            if(category.categoryInformation.categoryName != null){
//                var mapping = MapData().objectToMap(category.id.toString(), category)
//                databaseReference(AppConstance.CATEGORY_NODE).updateChildren(mapping)
                databaseReference(AppConstance.CATEGORY_NODE).updateChildren(MapData.objectToMap(category.id.toString(), category))
                    .addOnSuccessListener {
                        Log.d(TAG, "${AppConstance.ON_SUCCESS_LISTENER} : $it")
                    }
                    .addOnCompleteListener {
                        Log.d(TAG, "${AppConstance.ON_COMPLETE_LISTENER} : $it")
                        settingDataModel.state = SettingDataModel.SETTING_Z0230
                        activity.etCategoryName.setText("")
                        activity.etCategoryDescription.setText("")
                        activity.binding.progressing.visibility = View.GONE
                    }
                    .addOnCanceledListener {
                        Log.e(TAG, "${AppConstance.ON_CANCEL_LISTENER}")
                        PopupMsg.alert(activity, activity.getString(R.string.msg_access_deny))
                        activity.binding.progressing.visibility = View.GONE
                    }
                    .addOnFailureListener {
                        Log.e(TAG, "${AppConstance.ON_FAILURE_LISTENER} : $it")
                        PopupMsg.alert(activity, activity.getString(R.string.msg_cant_save))
                        activity.binding.progressing.visibility = View.GONE
                    }
            }else{
                Toast.makeText(activity, activity.getString(R.string.msg_category_name_blank), Toast.LENGTH_SHORT).show()
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }finally {
            Log.e(TAG, AppConstance.FINALLY)
        }
    }
    var item = ItemModel("", UserModel.UserSession(), ItemModel.ItemInformation(), CategoryInformation(),"")
    fun handleCreateNewItem(view : View){
        try{
            activity.binding.progressing.visibility = View.VISIBLE
            if(categoryList.size == 0){
                Toast.makeText(activity, activity.getString(R.string.msg_cant_save_item), Toast.LENGTH_SHORT).show()
                return
            }
            item.id = databaseReference(AppConstance.ITEM_NODE).toString()
            item.userSession.id = FirebaseConnection.firebaseAuth.uid
            item.userSession.token = System.currentTimeMillis().toString()
            item.itemInformation.id = databaseReference(AppConstance.ITEM_NODE).toString()
            item.itemInformation.itemName = activity.etItemName.text.toString().trim()
            item.itemInformation.itemDescription = activity.etItemDescription.text.toString()
            item.itemInformation.iconUrl = ""
            item.itemInformation.itemCode = activity.etItemCode.text.toString()
            item.categoryInformation = categoryInformationList.get(spinnerSelected)
            item.timestamp = System.currentTimeMillis().toString()

            if(item.itemInformation.itemName == null && item.itemInformation.itemCode == null)
                return
            else{
//                var mapping = MapData().objectToMap(item.id.toString(), item)
//                databaseReference(AppConstance.ITEM_NODE).updateChildren(mapping)
                databaseReference(AppConstance.ITEM_NODE).updateChildren(MapData.objectToMap(item.id.toString(), item))
                    .addOnSuccessListener {
                        Log.d(TAG, "${AppConstance.ON_SUCCESS_LISTENER} : $it")
                    }
                    .addOnCompleteListener{
                        Log.d(TAG, "${AppConstance.ON_COMPLETE_LISTENER} : $it")
                        settingDataModel.state = SettingDataModel.SETTING_Z0240
                        activity.etItemName.setText("")
                        activity.etItemDescription.setText("")
                        activity.etItemCode.setText("")
                        activity.binding.progressing.visibility = View.GONE
                    }
                    .addOnCanceledListener {
                        PopupMsg.alert(activity, activity.getString(R.string.msg_access_deny))
                        Log.e(TAG, "${AppConstance.ON_CANCEL_LISTENER}")
                        activity.binding.progressing.visibility = View.GONE
                    }
                    .addOnFailureListener {
                        Log.e(TAG, "${AppConstance.ON_FAILURE_LISTENER} : $it")
                        PopupMsg.alert(activity, activity.getString(R.string.msg_cant_save))
                        activity.binding.progressing.visibility = View.GONE
                    }
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }finally {
            Log.e(TAG, AppConstance.FINALLY)
        }
    }
}