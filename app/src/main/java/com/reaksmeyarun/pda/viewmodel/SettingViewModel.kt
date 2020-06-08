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
import com.reaksmeyarun.pda.adapter.ItemInformationAdapter
import com.reaksmeyarun.pda.adapter.SettingAdapter
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.firebase.FirebaseEmit
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.connection.FirebaseConnection.Companion.databaseReference
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.datamodel.SettingDataModel
import com.reaksmeyarun.pda.listener.*
import com.reaksmeyarun.pda.model.CategoryModel
import com.reaksmeyarun.pda.model.CategoryModel.*
import com.reaksmeyarun.pda.model.ItemModel
import com.reaksmeyarun.pda.model.ItemModel.*
import com.reaksmeyarun.pda.model.SettingModel
import com.reaksmeyarun.pda.preference.UserSession
import com.reaksmeyarun.pda.utils.MapDataUtils
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.view.activity.Z0200SettingActivity
import kotlinx.android.synthetic.main.activity_s0100_stock_inventory.*
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
//      Firebase
    private val firebaseEmit = FirebaseEmit()
//    private val firebaseEmitCategoryModel = FirebaseEmit()
//    private val firebaseEmitItemModel = FirebaseEmit()
//    private val firebaseEmitCategoryItemInformationModel = FirebaseEmit()
//    private val firebaseEmitItemCategoryInformationModel = FirebaseEmit()
//      arrayList
    var categoryModelListID = ArrayList<String>()
    var categoryModelListName = ArrayList<String>()
    var categoryModelListCategoryInformation = ArrayList<CategoryInformation>()
    var categoryItemInformation = ArrayList<ItemInformation>()
    var categoryItemInformationID = ArrayList<String>()
    var categoryModelList = ArrayList<CategoryModel>()
    var itemModelListID = ArrayList<String>()
    var itemModelList = ArrayList<ItemModel>()
    var itemListItemInformation = ArrayList<ItemInformation>()
    var settingDM = MyMutableLiveData<SettingDataModel>()
//      adapter
    val categoryAdapter = CategoryAdapter(activity, R.layout.item_category_layout)
    val itemAdapter = ItemAdapter(activity, R.layout.item_item_layout)
    val itemInformationAdapter = ItemInformationAdapter(activity, R.layout.item_item_layout)
    val spinnerAdapter = ArrayAdapter(activity, android.R.layout.simple_dropdown_item_1line, arrayListOf<String>())
//      listener
    var categoryOnClickListenerStatus : Int = 0
    var itemOnClickListenerStatus : Int = 0
//      selection
    var spinnerIndexSelected : Int = 0
//      status action
    var statusPush = false
    var statusCategoryPost = true
//      object
    var category = CategoryModel()
    var item = ItemModel()

    var oldItem = ItemModel()
    var newItem = ItemModel()

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
            settingDataModel.state = SettingDataModel.SETTING_Z0230
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
        val settingAdapter = SettingAdapter(activity, R.layout.item_setting_layout)
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
        val categoryItemAdapter = SettingAdapter(activity, R.layout.item_setting_layout)
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
    fun recyclerViewZ0230FirebaseChildListener(){
        firebaseEmit.getChild(
            activity,
            TAG,
            databaseReference(AppConstance.CATEGORY_NODE),
            object : FirebaseGetChildListener{
                override fun onCancelledListener(databaseError: DatabaseError) {
//                    Do nothing
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot) {
//                    Do nothing
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot) {
                    dataSnapshot.getValue(CategoryModel::class.java)?.let {
                        var index = categoryModelListID.indexOf(it.categoryInformation.id)
                        categoryModelList[index] = it
                        categoryAdapter.replace(index, it)
                    }
                }

                override fun onChildAdded(dataSnapshot: DataSnapshot) {
                    dataSnapshot.getValue(CategoryModel::class.java)?.let {
                        it.categoryInformation.id?.let { element -> categoryModelListID.add(element) }
                        it.categoryInformation.categoryName?.let { element -> categoryModelListName.add(element) }
                        categoryModelList.add(it)
                        categoryModelListCategoryInformation.add(it.categoryInformation)
                        categoryAdapter.addItem(it)
                        spinnerAdapter.add(it.categoryInformation.categoryName)
                    }
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                    dataSnapshot.getValue(CategoryModel::class.java)?.let {
                        spinnerAdapter.remove(it.categoryInformation.categoryName)
                    }
                }
            }
        )
//        firebaseEmit.firebaseGetChildListener = object : FirebaseGetChildListener{
////        firebaseEmitCategoryModel.getChild(activity, TAG, databaseReference(AppConstance.CATEGORY_NODE))
////        firebaseEmitCategoryModel.firebaseGetChildListener = object : FirebaseGetChildListener{
//            override fun onCancelledListener(databaseError: DatabaseError) {
////              Do nothing with this feature
//            }
//
//            override fun onChildMoved(dataSnapshot: DataSnapshot) {
////              Do nothing with this feature
//            }
//
//            override fun onChildChanged(dataSnapshot: DataSnapshot) {
//                dataSnapshot.getValue(CategoryModel::class.java)?.let {
//                    var index = categoryModelListID.indexOf(it.categoryInformation.id)
//                    categoryModelList[index] = it
//                    categoryAdapter.replace(index, it)
//                }
//            }
//
//            override fun onChildAdded(dataSnapshot: DataSnapshot) {
//                dataSnapshot.getValue(CategoryModel::class.java)?.let {
//                    it.categoryInformation.id?.let { element -> categoryModelListID.add(element) }
//                    it.categoryInformation.categoryName?.let { element -> categoryModelListName.add(element) }
//                    categoryModelList.add(it)
//                    categoryModelListCategoryInformation.add(it.categoryInformation)
//                    categoryAdapter.addItem(it)
//                    spinnerAdapter.add(it.categoryInformation.categoryName)
//                }
//            }
//
//            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
//                dataSnapshot.getValue(CategoryModel::class.java)?.let {
//                    spinnerAdapter.remove(it.categoryInformation.categoryName)
//                }
//            }
//        }
    }

    fun recyclerViewZ0240FirebaseChildListener(){
        firebaseEmit.getChild(
            activity,
            TAG,
            databaseReference(AppConstance.ITEM_NODE),
            object : FirebaseGetChildListener{
                override fun onCancelledListener(databaseError: DatabaseError) {
//                    Do nothing
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot) {
//                    Do nothing
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot) {
                    dataSnapshot.getValue(CategoryModel::class.java)?.let {
                        var index = categoryModelListID.indexOf(category.categoryInformation.id!!)
                        categoryModelList[index] = it
                        categoryAdapter.replace(index, it)
                    }
                }

                override fun onChildAdded(dataSnapshot: DataSnapshot) {
                    dataSnapshot.getValue(ItemModel::class.java)?.let {
                        it.itemInformation.id?.let {
                                element -> itemModelListID.add(element)
                        }
                        itemModelList.add(it)
                        itemListItemInformation.add(it.itemInformation)
                        itemAdapter.addItem(it)

                        if(!statusPush)
                            return
                        var data = ArrayList<ItemInformation>()
                        data.addAll(itemListItemInformation)
//                    Firebase emit
                        firebaseEmit
//                    firebaseEmitCategoryModel
                            .push(
                                activity,
                                TAG,
                                databaseReference(AppConstance.CATEGORY_NODE)
                                    .child(it.categoryInformation.id.toString())
                                    .child(AppConstance.ITEM_INFORMATION_NODE),
                                MapDataUtils
                                    .objectToMap(
                                        it.itemInformation.id!!,
                                        it.itemInformation),
                                object : FireBaseListener{
                                    override fun onCancelListener() {
                                        statusPush = true
                                    }

                                    override fun onFailureListener() {
                                        statusPush = true
                                    }

                                    override fun onSuccessListener() {
//                                  Do nothing
                                    }

                                    override fun onCompleteListener() {
                                        statusPush = false
                                        activity.progressing.visibility = View.GONE
                                    }
                                }
                            )
                    }
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {
//                    Do nothing
                }
            }
        )
//        firebaseEmit.firebaseGetChildListener = object : FirebaseGetChildListener{
////        firebaseEmitItemModel.getChild(activity, TAG, databaseReference(AppConstance.ITEM_NODE))
////        firebaseEmitItemModel.firebaseGetChildListener = object : FirebaseGetChildListener{
//            override fun onCancelledListener(databaseError: DatabaseError) {
////              Do nothing with this feature
//            }
//
//            override fun onChildMoved(dataSnapshot: DataSnapshot) {
////              Do nothing with this feature
//            }
//
//            override fun onChildChanged(dataSnapshot: DataSnapshot) {
//                dataSnapshot.getValue(CategoryModel::class.java)?.let {
//                    var index = categoryModelListID.indexOf(category.categoryInformation.id!!)
//                    categoryModelList[index] = it
//                    categoryAdapter.replace(index, it)
//                }
//            }
//
//            override fun onChildAdded(dataSnapshot: DataSnapshot) {
//                dataSnapshot.getValue(ItemModel::class.java)?.let {
//                    it.itemInformation.id?.let {
//                            element -> itemModelListID.add(element)
//                    }
//                    itemModelList.add(it)
//                    itemListItemInformation.add(it.itemInformation)
//                    itemAdapter.addItem(it)
//
//                    if(!statusPush)
//                        return
//                    var data = ArrayList<ItemInformation>()
//                    data.addAll(itemListItemInformation)
////                    Firebase emit
//                    firebaseEmit
////                    firebaseEmitCategoryModel
//                        .push(
//                            activity,
//                            TAG,
//                            databaseReference(AppConstance.CATEGORY_NODE)
//                                .child(it.categoryInformation.id.toString())
//                                .child(AppConstance.ITEM_INFORMATION_NODE),
//                                MapDataUtils
//                                    .objectToMap(
//                                        it.itemInformation.id!!,
//                                        it.itemInformation),
//                            object : FireBaseListener{
//                                override fun onCancelListener() {
//                                    statusPush = true
//                                }
//
//                                override fun onFailureListener() {
//                                    statusPush = true
//                                }
//
//                                override fun onSuccessListener() {
////                                  Do nothing
//                                }
//
//                                override fun onCompleteListener() {
//                                    statusPush = false
//                                    activity.progressing.visibility = View.GONE
//                                }
//                            }
//                        )
//                }
//            }
//
//            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
////                Do nothing
//            }
//        }
    }
    var onEditCategoryInformation = CategoryInformation()

    fun handleEditCategory(view : View){
        if(itemInformationAdapter.items.size > 0)
            itemInformationAdapter.clearAll()
//        binding object
        onEditCategoryInformation.id = category.categoryInformation.id
        onEditCategoryInformation.categoryName = activity.etCategoryName.text.toString()
        onEditCategoryInformation.categoryDescription = activity.etCategoryDescription.text.toString()
//        firebase emit
        firebaseEmit
//        firebaseEmitCategoryItemInformationModel
            .edit(
                activity,
                TAG,
                databaseReference(AppConstance.CATEGORY_NODE)
                    .child(category.categoryInformation.id!!)
                    .child(AppConstance.CATEGORY_INFORMATION_NODE),
                onEditCategoryInformation,
                object : FireBaseListener{
                    override fun onCancelListener() {
//                        Do nothing
                    }

                    override fun onFailureListener() {
//                        Do nothing
                    }

                    override fun onSuccessListener() {
//                  change value on recyclerView with specific index
                        for (id in categoryItemInformationID){
                            firebaseEmit
//                            firebaseEmitItemCategoryInformationModel
                                .edit(
                                    activity,
                                    TAG,
                                    databaseReference(AppConstance.ITEM_NODE)
                                        .child(id)
                                        .child(AppConstance.CATEGORY_INFORMATION_NODE),
                                    onEditCategoryInformation,
                                    object : FireBaseListener{
                                        override fun onCancelListener() {
//                                            Do nothing
                                        }

                                        override fun onFailureListener() {
//                                            Do nothing
                                        }

                                        override fun onSuccessListener() {
//                                            Do nothing
                                        }

                                        override fun onCompleteListener() {
                                            if(categoryItemInformationID[categoryItemInformationID.indexOf(id)]
                                                == itemListItemInformation[categoryItemInformationID.indexOf(id)].id) {
                                                oldItem = itemModelList[categoryItemInformationID.indexOf(id)]
                                                newItem.itemInformation = oldItem.itemInformation
                                                newItem.userSession = oldItem.userSession
                                                newItem.timestamp = oldItem.timestamp
                                                newItem.categoryInformation = onEditCategoryInformation
                                                itemAdapter.replace(itemModelListID.indexOf(newItem.itemInformation.id!!), newItem)
                                            }
                                        }

                                    }
                                )
                        }
                    }

                    override fun onCompleteListener() {
                        settingDataModel.state = SettingDataModel.SETTING_Z0230
                        activity.btnEditCategory.visibility = View.GONE
                        activity.btnCreateNewCategory.visibility = View.VISIBLE
                    }

                }
            )
    }
    fun handleCreateNewCategory(view : View){
        try{
            activity.binding.progressing.visibility =  View.VISIBLE
            if(itemInformationAdapter.items.size > 0)
                itemInformationAdapter.clearAll()
//          binding data to object
            category.userSession.id = FirebaseConnection.firebaseAuth.uid
            category.userSession.token = UserSession.getInstance(activity).getToken()
            category.categoryInformation.id = databaseReference(AppConstance.CATEGORY_NODE).push().key
            category.categoryInformation.categoryName = activity.etCategoryName.text.toString().trim()
            category.categoryInformation.categoryDescription = activity.etCategoryDescription.text.toString().trim()
            category.status = AppConstance.CAN_DELETE
            category.timestamp = System.currentTimeMillis().toString()

            if(category.categoryInformation.categoryName != null && category.categoryInformation.categoryName!!.isNotEmpty()) {
                firebaseEmit
//                firebaseEmitCategoryModel.push(
                    .push(
                        activity,
                        TAG,
                        databaseReference(AppConstance.CATEGORY_NODE),
                        MapDataUtils.objectToMap(
                            category.categoryInformation.id.toString(),
                            category
                        ),
                        object : FireBaseListener{
                            override fun onCancelListener() {
//                            Do nothing
                            }

                            override fun onFailureListener() {
//                            Do nothing
                            }

                            override fun onSuccessListener() {
                                statusCategoryPost = false
                            }

                            override fun onCompleteListener() {
                                settingDataModel.state = SettingDataModel.SETTING_Z0230
                                activity.etCategoryName.setText("")
                                activity.etCategoryDescription.setText("")
                            }
                        }
                    )
            }else
                Toast.makeText(activity, activity.getString(R.string.msg_category_name_blank), Toast.LENGTH_SHORT).show()
            activity.binding.progressing.visibility = View.GONE
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }finally {
            Log.e(TAG, AppConstance.FINALLY)
        }
    }
    fun handleEditItem(view : View){
        activity.btnCreateNewCategory.visibility = View.VISIBLE
        activity.btnEditCategory.visibility = View.GONE
    }

    fun handleCreateNewItem(view : View){
        try{
            statusPush = true
            activity.binding.progressing.visibility = View.VISIBLE
            if(categoryModelListName.size == 0){
                PopupMsg
                    .alert(
                        activity,
                        activity.getString(R.string.msg_dont_have_category),
                        object : PopupMsg.OnClickButtonYesNoCallBack{
                            override fun onYesCallBack() {
                                activity.progressing.visibility = View.VISIBLE
                                android.os.Handler().postDelayed({
                                    settingDataModel.state = SettingDataModel.SETTING_Z0231
                                    activity.progressing.visibility = View.GONE
                                },300)
                            }

                            override fun onNoCallBack() {
                                activity.progressing.visibility = View.VISIBLE
                                android.os.Handler().postDelayed({
                                    settingDataModel.state = SettingDataModel.SETTING_Z0240
                                    activity.progressing.visibility = View.GONE
                                },300)
                            }
                        }
                    )
            }
//          binding data to object
            item.userSession.id = FirebaseConnection.firebaseAuth.uid
            item.userSession.token = UserSession.getInstance(activity).getToken()
            item.itemInformation.id = databaseReference(AppConstance.ITEM_NODE).push().key
            item.itemInformation.itemName = activity.etItemName.text.toString().trim()
            item.itemInformation.itemDescription = activity.etItemDescription.text.toString()
            item.itemInformation.iconUrl = AppConstance.NO_IMAGE_URL
            item.itemInformation.itemCode = activity.etItemCode.text.toString()
            item.categoryInformation = categoryModelListCategoryInformation[spinnerIndexSelected]
            item.timestamp = System.currentTimeMillis().toString()

            if(item.itemInformation.itemName == null || item.itemInformation.itemCode == null) {
                PopupMsg.alert(
                    activity,
                    activity.getString(R.string.msg_something_wrong)
                )
                return
            }else{
//                firebaseEmitItemModel
                firebaseEmit
                    .push(
                        activity,
                        TAG,
                        databaseReference(AppConstance.ITEM_NODE),
                        MapDataUtils.objectToMap(
                            item.itemInformation.id!!,
                            item
                        ),
                        object : FireBaseListener{
                            override fun onCancelListener() {
                                activity.binding.progressing.visibility = View.GONE
                            }

                            override fun onFailureListener() {
                                activity.binding.progressing.visibility = View.GONE
                            }

                            override fun onSuccessListener() {
//                              Do nothing
                            }

                            override fun onCompleteListener() {
                                settingDataModel.state = SettingDataModel.SETTING_Z0240
                                activity.etItemName.setText("")
                                activity.etItemDescription.setText("")
                                activity.etItemCode.setText("")
                                activity.binding.progressing.visibility = View.GONE
                            }

                        }
                    )
            }
        }catch (ex : Exception){
            Log.e(TAG, "${AppConstance.EXCEPTION} : $ex")
        }catch (ex : ExceptionInInitializerError){
            Log.e(TAG, "${AppConstance.EXCEPTION_IN_INITIALIZER_ERROR} : $ex")
        }finally {
            Log.e(TAG, AppConstance.FINALLY)
        }
    }

    fun bindingAdapter(){
//      Spinner
        activity.spinner.adapter = spinnerAdapter
        activity.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerIndexSelected = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                spinnerIndexSelected = 0
            }
        }

//      CategoryRvZ0230
        activity.rvZ0230.adapter = categoryAdapter
        activity.rvZ0230.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        categoryAdapter.addItem(categoryModelList)
        categoryAdapter.onClickRecyclerViewListener = object : CategoryAdapter.OnClickRecyclerViewListener {
            override fun onClickListener(pos: Int, data: CategoryModel) {
                Log.d(TAG, "${AppConstance.CLICK_LISTENER} : $pos : $data ")
//                visibility
                activity.btnCreateNewCategory.visibility = View.GONE
                activity.btnEditCategory.visibility = View.GONE
//                clickable
                activity.etCategoryName.isClickable = false
                activity.etCategoryDescription.isClickable = false
//                bindingText
                activity.etCategoryName.setText(data.categoryInformation.categoryName ?: "")
                activity.etCategoryDescription.setText(data.categoryInformation.categoryDescription ?: "")
//                status
                categoryOnClickListenerStatus = BaseAdapter.STATUS_ONCLICKLISTENER
                settingDataModel.state = SettingDataModel.SETTING_Z0231
//                bindingAdapterCategoryItemInformation
//                itemInformationAdapter.addNewItem(data.itemInformation)
            }

            override fun onEditListener(pos: Int, data: CategoryModel) {
                Log.d(TAG, "${AppConstance.EDIT_LISTENER} : $pos : $data ")
//                visibility
                activity.btnCreateNewCategory.visibility = View.GONE
                activity.btnEditCategory.visibility = View.VISIBLE
//                clickable
                activity.etCategoryName.isClickable = true
                activity.etCategoryDescription.isClickable = true
//                binding text
                activity.etCategoryName.setText(data.categoryInformation.categoryName ?: "")
                activity.etCategoryDescription.setText(data.categoryInformation.categoryDescription ?: "")
//                binding obj
                category = data
//                status
                categoryOnClickListenerStatus = BaseAdapter.STATUS_ONEDITLISTENER
                settingDataModel.state = SettingDataModel.SETTING_Z0231
//                bindingAdapterCategoryItemInformation
                itemInformationAdapter.clearAll()
//                firebaseEmitCategoryItemInformationModel
                firebaseEmit
                    .getChild(
                        activity,
                        TAG,
                        databaseReference(AppConstance.CATEGORY_NODE)
                            .child(data.categoryInformation.id!!)
                            .child(AppConstance.ITEM_INFORMATION_NODE),
                        object : FirebaseGetChildListener{
                            override fun onCancelledListener(databaseError: DatabaseError) {
//                                Do nothing
                            }

                            override fun onChildMoved(dataSnapshot: DataSnapshot) {
//                                Do nothing
                            }

                            override fun onChildChanged(dataSnapshot: DataSnapshot) {
                                dataSnapshot.getValue(ItemInformation::class.java)?.let {itemInformation->
                                    itemInformationAdapter
                                        .replace(
                                            categoryItemInformationID
                                                .indexOf(itemInformation.id),
                                            itemInformation)
                                }
                            }

                            override fun onChildAdded(dataSnapshot: DataSnapshot) {
                                dataSnapshot.getValue(ItemInformation::class.java)?.let {
                                    categoryItemInformationID.add(it.id!!)
                                    itemInformationAdapter.addItem(it)
                                }
                            }

                            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
//                                Do nothing
                            }
                        }
                    )
//                firebaseEmitCategoryItemInformationModel.firebaseGetChildListener = object : FirebaseGetChildListener{
//                firebaseEmit.firebaseGetChildListener = object : FirebaseGetChildListener{
//                    override fun onCancelledListener(databaseError: DatabaseError) {
////                        Do nothing
//                    }
//
//                    override fun onChildMoved(dataSnapshot: DataSnapshot) {
////                        Do nothing
//                    }
//
//                    override fun onChildChanged(dataSnapshot: DataSnapshot) {
//                        dataSnapshot.getValue(ItemInformation::class.java)?.let {itemInformation->
//                            itemInformationAdapter
//                                .replace(
//                                    categoryItemInformationID
//                                        .indexOf(itemInformation.id),
//                                    itemInformation)
//                        }
//                    }
//
//                    override fun onChildAdded(dataSnapshot: DataSnapshot) {
//                        dataSnapshot.getValue(ItemInformation::class.java)?.let {
//                            categoryItemInformationID.add(it.id!!)
//                            itemInformationAdapter.addItem(it) }
//                    }
//
//                    override fun onChildRemoved(dataSnapshot: DataSnapshot) {
////                        Do nothing
//                    }
//                }
            }

            override fun onDeleteListener(pos: Int, data: CategoryModel) {
//                if(data.itemInformation.size > 0){
//                    PopupMsg.alert(activity, activity.getString(R.string.msg_cant_delete_category))
//                    return
//                }

                Log.d(TAG, "${AppConstance.DELETE_LISTENER} : $pos : $data ")
                PopupMsg.alert(
                    activity,
                    activity.getString(R.string.msg_delete),
                    object : PopupMsg.OnClickButtonYesNoCallBack{
                        override fun onYesCallBack() {
//                            firebaseEmitCategoryModel
                            firebaseEmit
                                .delete(
                                    activity,
                                    TAG.plus(AppConstance.CAN_DELETE),
                                    databaseReference(AppConstance.CATEGORY_NODE)
                                        .child(data.categoryInformation.id!!),
                                    object : FireBaseListener{
                                        override fun onCancelListener() {
//                                          Do nothing
                                        }

                                        override fun onFailureListener() {
                                            PopupMsg.alert(activity, activity.getString(R.string.msg_something_wrong))
                                        }

                                        override fun onSuccessListener() {
//                                          Do nothing
                                        }

                                        override fun onCompleteListener() {
                                            categoryModelList.remove(data)
                                            categoryModelListID.remove(data.categoryInformation.id!!)
                                            categoryAdapter.removeItem(pos)
                                        }
                                    }
                                )
                        }

                        override fun onNoCallBack() {
//                          Do nothing
                        }
                    }
                )
            }
        }

//      CategoryItemInformationRvZ0231
        activity.rvZ0231CategoryItemInformation.adapter = itemInformationAdapter
        activity.rvZ0231CategoryItemInformation.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        itemInformationAdapter.addItem(categoryItemInformation)
        itemInformationAdapter.onClickRecyclerViewListener = object : ItemInformationAdapter.OnClickRecyclerViewListener {
            override fun onClickListener(pos: Int, data: ItemInformation) {
                Log.d(TAG, "${AppConstance.CLICK_LISTENER} : $pos : $data ")
            }

            override fun onEditListener(pos: Int, data: ItemInformation) {
                Log.d(TAG, "${AppConstance.EDIT_LISTENER} : $pos : $data ")
            }

            override fun onDeleteListener(pos: Int, data: ItemInformation) {
                Log.d(TAG, "${AppConstance.DELETE_LISTENER} : $pos : $data ")
                data?.let {
                    PopupMsg.alert(
                        activity,
                        activity.getString(R.string.msg_delete),
                        object : PopupMsg.OnClickButtonYesNoCallBack{
                            override fun onYesCallBack() {
//                              adapter
                                itemInformationAdapter.removeItem(pos)
//                              Firebase emit
//                              firebaseEmitCategoryItemInformationModel
                                firebaseEmit
                                    .delete(
                                        activity,
                                        TAG,
                                        databaseReference(AppConstance.CATEGORY_INFORMATION_NODE)
                                            .child(AppConstance.ITEM_INFORMATION_NODE)
                                            .child(data.id!!),
                                        object : FireBaseListener{
                                            override fun onCancelListener() {
//                                              Do nothing
                                            }

                                            override fun onFailureListener() {
//                                              Do nothing
                                            }

                                            override fun onSuccessListener() {
//                                              firebaseEmitItemCategoryInformationModel
                                                firebaseEmit
                                                    .delete(
                                                        activity,
                                                        TAG,
                                                        databaseReference(AppConstance.ITEM_INFORMATION_NODE)
                                                            .child(AppConstance.CATEGORY_INFORMATION_NODE),
                                                        object : FireBaseListener{
                                                            override fun onCancelListener() {
//                                                              Do nothing
                                                            }

                                                            override fun onFailureListener() {
//                                                              Do nothing
                                                            }

                                                            override fun onSuccessListener() {
//                                                              Do nothing
                                                            }

                                                            override fun onCompleteListener() {
//                                                              Do nothing
                                                            }
                                                        }
                                                    )
                                            }

                                            override fun onCompleteListener() {
//                                      Do nothing
                                            }

                                        }
                                    )
                            }

                            override fun onNoCallBack() {
//                              Do nothing
                            }
                        }
                    )
                }
            }
        }

//      ItemRvZ0240
        activity.rvZ0240.adapter = itemAdapter
        activity.rvZ0240.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        itemAdapter.addItem(itemModelList)
        itemAdapter.onClickRecyclerViewListener = object : ItemAdapter.OnClickRecyclerViewListener{
            override fun onClickListener(pos: Int, data: ItemModel) {
                Log.d(TAG, "${AppConstance.CLICK_LISTENER} : $pos : $data ")
//                visibility
                activity.btnCreateNewCategory.visibility = View.GONE
                activity.btnEditCategory.visibility = View.GONE
//                TODO : clickable
//                    activity.etItemCode.isClickable = false
//                    activity.etItemName.isClickable = false
//                    activity.etItemDescription.isClickable = false
//                binding text
                activity.etItemCode.setText(data.itemInformation.itemCode ?: "")
                activity.etItemName.setText(data.itemInformation.itemName ?: "")
                activity.etItemDescription.setText(data.itemInformation.itemDescription ?: "")
//                binding selectSpinner
                if(data.categoryInformation!=null)
                    activity.spinner.setSelection(categoryModelListName.indexOf(data.categoryInformation.categoryName));
//                status
                itemOnClickListenerStatus = BaseAdapter.STATUS_ONCLICKLISTENER
                settingDataModel.state = SettingDataModel.SETTING_Z0241
            }

            override fun onEditListener(pos: Int, data: ItemModel) {
                Log.d(TAG, "${AppConstance.EDIT_LISTENER} : $pos : $data ")
//                visibility
                activity.btnCreateNewCategory.visibility = View.GONE
                activity.btnEditCategory.visibility = View.VISIBLE
//                TODO : clickable
//                    activity.etItemCode.isClickable = true
//                    activity.etItemName.isClickable = true
//                    activity.etCategoryDescription.isClickable = true
//                binding text
                activity.etItemCode.setText(data.itemInformation.itemCode ?: "")
                activity.etItemName.setText(data.itemInformation.itemName ?: "")
                activity.etCategoryDescription.setText(data.itemInformation.itemDescription ?: "")
//                status
                itemOnClickListenerStatus = BaseAdapter.STATUS_ONEDITLISTENER
                settingDataModel.state = SettingDataModel.SETTING_Z0241

            }

            override fun onDeleteListener(pos: Int, data: ItemModel) {
                Log.d(TAG, "${AppConstance.DELETE_LISTENER} : $pos : $data ")
                PopupMsg.alert(
                    activity,
                    activity.getString(R.string.msg_delete),
                    object : PopupMsg.OnClickButtonYesNoCallBack{
                        override fun onYesCallBack() {
//                      firebase emit
//                        firebaseEmitItemCategoryInformationModel
                            firebaseEmit
                                .delete(
                                    activity,
                                    TAG,
                                    databaseReference(AppConstance.ITEM_NODE)
                                        .child(data.itemInformation.id!!),
                                    object : FireBaseListener{
                                        override fun onCancelListener() {
//                                        Do nothing
                                        }

                                        override fun onFailureListener() {
//                                        Do nothing
                                        }

                                        override fun onSuccessListener() {
//                                      firebase emit
//                                        firebaseEmitCategoryItemInformationModel
                                            firebaseEmit
                                                .delete(
                                                    activity,
                                                    TAG,
                                                    databaseReference(AppConstance.CATEGORY_NODE)
                                                        .child(data.categoryInformation.id!!)
                                                        .child(AppConstance.ITEM_INFORMATION_NODE)
                                                        .child(data.itemInformation.id!!),
                                                    object : FireBaseListener{
                                                        override fun onCancelListener() {
//                                                      Do nothing
                                                        }

                                                        override fun onFailureListener() {
//                                                      Do nothing
                                                        }

                                                        override fun onSuccessListener() {
//                                                      Do nothing
                                                        }

                                                        override fun onCompleteListener() {
//                                                      adapter
                                                            itemAdapter.removeItem(pos)
                                                        }
                                                    })
                                        }

                                        override fun onCompleteListener() {
//                                        Do nothing
                                        }
                                    }
                                )
                        }
                        override fun onNoCallBack() {
//                        DO nothing
                        }
                    }
                )
            }
        }
    }
}