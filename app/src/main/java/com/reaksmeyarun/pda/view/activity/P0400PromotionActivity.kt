package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.post.transfer.lanpost.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.databinding.ActivityP0400PromotionBinding
import com.reaksmeyarun.pda.viewmodel.PromotionViewModel
import kotlinx.android.synthetic.main.activity_p0200_home.*

class P0400PromotionActivity : BaseActivity() {
    lateinit var binding : ActivityP0400PromotionBinding
    lateinit var vmPromotion : PromotionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p0400_promotion)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_basecolor)
        vmPromotion = PromotionViewModel(this)
        binding.vmPromotion = vmPromotion
        binding.lifecycleOwner = this
        vmPromotion.bindingPromotion()
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }
}
