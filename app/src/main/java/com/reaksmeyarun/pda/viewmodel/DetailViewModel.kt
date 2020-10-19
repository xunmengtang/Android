package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.DetailDataModel
import com.reaksmeyarun.pda.firebaseRepo.RequestItem
import com.reaksmeyarun.pda.firebaseRepo.order.RequestAddToCart
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.preference.UserSession
import com.reaksmeyarun.pda.utils.ConvertUtils
import com.reaksmeyarun.pda.utils.MapDataUtils
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

    fun handleAddToCart(view : View){

    }

    fun requestAddToCart(){
        val requestAddToCart = RequestAddToCart(activity)
        requestAddToCart.model = RequestAddToCart.RequestClearCartModel()
        requestAddToCart.model.orderId = UserSession.getInstance(activity).getUserId()
//        requestAddToCart.model.mutable
        requestAddToCart.listener(object : FireBaseListener{
            override fun onFailureListener() {

            }

            override fun <TResult> onCompleteListener(task: Task<TResult>) {

            }
        })
        requestAddToCart.execute()
    }
}