package com.reaksmeyarun.pda.viewmodel

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.PromotionAdapter
import com.reaksmeyarun.pda.model.PromotionModel
import com.reaksmeyarun.pda.view.activity.P0400PromotionActivity
import kotlinx.android.synthetic.main.activity_p0400_promotion.*

class PromotionViewModel(var activity : P0400PromotionActivity) : ViewModel() {
    private val TAG = "PromotionViewModel"
    var promotionList = ArrayList<PromotionModel>()
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