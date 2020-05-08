package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.ig.iginnovation.superapp.driver.mycustomclass.MyMutableLiveData
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.PromotionAdapter
import com.reaksmeyarun.pda.datamodel.PromotionDataModel
import com.reaksmeyarun.pda.model.PromotionModel
import com.reaksmeyarun.pda.view.activity.P0400PromotionActivity
import kotlinx.android.synthetic.main.p0410_promotion_layout.*

class PromotionViewModel(var promotionDataModel : PromotionDataModel, var activity : P0400PromotionActivity) : ViewModel() {
    private val TAG = "PromotionViewModel"
    var promotionList = ArrayList<PromotionModel>()
    var promotionDM = MyMutableLiveData<PromotionDataModel>()
    init {
        promotionDM.setValue(promotionDataModel)
        promotionDataModel.state = PromotionDataModel.PROMOTION_P0410
    }
    fun handleBackPress(view: View){
        activity.finish()
    }
    fun handlePromotionScreen(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            promotionDataModel.state = PromotionDataModel.PROMOTION_P0410
        },300)
    }
    fun handlePromotionDetailScreen(view: View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            promotionDataModel.state = PromotionDataModel.PROMOTION_P0420
        },300)
    }
    fun bindingPromotion(){
        for(value in 1..10){
            promotionList.add(PromotionModel(""))
        }
        var promotionAdapter = PromotionAdapter(activity, R.layout.item_promotion_layout)
        activity.rvPromotion.adapter = promotionAdapter
        activity.rvPromotion.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        promotionAdapter.addItem(promotionList)
    }
}