package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Task
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.DetailDataModel
import com.reaksmeyarun.pda.firebaseRepo.RequestCart
import com.reaksmeyarun.pda.firebaseRepo.RequestItem
import com.reaksmeyarun.pda.firebaseRepo.order.RequestAddToCart
import com.reaksmeyarun.pda.listener.FireBaseListener
import com.reaksmeyarun.pda.preference.UserSession
import com.reaksmeyarun.pda.utils.ConvertUtils
import com.reaksmeyarun.pda.utils.MapDataUtils
import com.reaksmeyarun.pda.view.activity.DetailActivity

class DetailViewModel(var detailDataModel : DetailDataModel, var activity : DetailActivity) : ViewModel(){

    var detailData = MyMutableLiveData<DetailDataModel>()
    var data = RequestItem.ResponseItem()
    var key : String ?= ""

    init {
        detailData.setValue(detailDataModel)
    }

    fun initItemInformation(stringData : String?){
        if(stringData!=null){
            data = ConvertUtils.init().stringToObject(stringData, RequestItem.ResponseItem()::class.java) as RequestItem.ResponseItem
            detailDataModel.itemName = data.itemName
            detailDataModel.itemPrice = data.price.toString()
            detailDataModel.itemDesc = data.desc
            Glide.with(activity).load(data.image?.url).into(activity.binding.icon)
        }
    }

    fun handleAddToCart(view : View){
        requestAddToCart()
    }

    fun handleAddToFavorite(view : View){

    }

    fun requestAddToCart(){
        val requestAddToCart = RequestAddToCart(activity)
        requestAddToCart.model = RequestAddToCart.RequestClearCartModel()
        key = requestAddToCart.key()
        requestAddToCart.model.orderId = UserSession.getInstance(activity).getUserId()
        requestAddToCart.model.mutable = MapDataUtils.objectToMap(
            key ?: "",
            RequestCart.ResponseCart(
                key ?: "",
                data,
                (activity.binding.quanitites.text.toString()).toInt()
            )
        )
        requestAddToCart.listener(object : FireBaseListener{
            override fun onFailureListener() {

            }

            override fun <TResult> onCompleteListener(task: Task<TResult>) {
                activity.finish()
            }
        })
        requestAddToCart.execute()
    }

    fun handleAddQuanitties(view : View){
        activity.binding.quanitites.text = (activity.binding.quanitites.text.toString().toInt() + 1).toString()
        activity.binding.itemPrice.text = "$ ${activity.binding.quanitites.text.toString().toFloat() * data.price!!}"
    }

    fun handleRemoveQuanitties(view : View){
        if(activity.binding.quanitites.text.toString().toInt() > 1){
            activity.binding.quanitites.text = (activity.binding.quanitites.text.toString().toInt() - 1).toString()
            activity.binding.itemPrice.text = "$ ${activity.binding.quanitites.text.toString().toFloat() * data.price!!}"
        }
    }
}