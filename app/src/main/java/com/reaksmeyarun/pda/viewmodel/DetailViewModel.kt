package com.reaksmeyarun.pda.viewmodel

import androidx.lifecycle.ViewModel
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.DetailDataModel
import com.reaksmeyarun.pda.firebaseRepo.RequestItem
import com.reaksmeyarun.pda.utils.ConvertUtils
import com.reaksmeyarun.pda.view.activity.DetailActivity

class DetailViewModel(var detailDataModel : DetailDataModel, var activity : DetailActivity) : ViewModel(){

    var detailData = MyMutableLiveData<DetailDataModel>()

    init {
        detailData.setValue(detailDataModel)
    }

    fun initItemInformation(stringData : String?){
        if(stringData!=null){
            val data =
                ConvertUtils.init().stringToObject(stringData, RequestItem.ResponseItem()::class.java) as RequestItem.ResponseItem
            detailDataModel.itemName = data.itemName
            detailDataModel.itemPrice = data.price.toString()
            detailDataModel.itemDesc = data.desc
        }
    }
}