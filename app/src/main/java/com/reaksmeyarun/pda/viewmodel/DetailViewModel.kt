package com.reaksmeyarun.pda.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
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

    init {
        detailData.setValue(detailDataModel)
    }

    fun initItemInformation(stringData : String?){
        if(stringData!=null){
            data = ConvertUtils.init().stringToObject(stringData, RequestItem.ResponseItem()::class.java) as RequestItem.ResponseItem
            detailDataModel.itemName = data.itemName
            detailDataModel.itemPrice = data.price.toString()
            detailDataModel.itemDesc = data.desc
            Log.d("TAG","Hello price ${detailDataModel.itemPrice}")
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
        requestAddToCart.model.orderId = UserSession.getInstance(activity).getUserId()
        requestAddToCart.model.mutable = MapDataUtils.objectToMap(
            requestAddToCart.key(),
            RequestCart.ResponseCart(
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
        Log.d("TAG","this is price ${activity.binding.quanitites.text}")
    }

    fun handleRemoveQuanitties(view : View){
        if(activity.binding.quanitites.text.toString().toInt() > 1)
            activity.binding.quanitites.text = (activity.binding.quanitites.text.toString().toInt() - 1).toString()
    }
}