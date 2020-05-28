package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.databinding.ActivityP0400PromotionBinding
import com.reaksmeyarun.pda.datamodel.PromotionDataModel
import com.reaksmeyarun.pda.viewmodel.PromotionViewModel

class P0400PromotionActivity : BaseActivity() {
    lateinit var binding : ActivityP0400PromotionBinding
    lateinit var vmPromotion : PromotionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p0400_promotion)
        vmPromotion = PromotionViewModel(PromotionDataModel(),this)
        binding.vmPromotion = vmPromotion
        binding.lifecycleOwner = this
        vmPromotion.bindingPromotion()
    }
}
