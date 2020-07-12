package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.ItemViewDataModel
import com.reaksmeyarun.pda.datamodel.ItemViewDataModel.Companion.ITEM_I0200
import com.reaksmeyarun.pda.datamodel.ItemViewDataModel.Companion.ITEM_I0300
import com.reaksmeyarun.pda.datamodel.ItemViewDataModel.Companion.ITEM_I0310
import com.reaksmeyarun.pda.base.BaseFirebaseEvenListener
import com.reaksmeyarun.pda.view.activity.I0100ItemActivity

class ItemViewModel (var itemViewDataModel : ItemViewDataModel, var activity : I0100ItemActivity) : ViewModel() {
    val TAG = "ItemViewModel"
//    live data
    var itemData = MyMutableLiveData<ItemViewDataModel>()
//  firebase
    private val firebaseEmit =
    BaseFirebaseEvenListener()
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
}