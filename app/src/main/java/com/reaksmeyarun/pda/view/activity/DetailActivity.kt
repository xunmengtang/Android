package com.reaksmeyarun.pda.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.databinding.ActivityDetailBinding
import com.reaksmeyarun.pda.datamodel.DetailDataModel
import com.reaksmeyarun.pda.viewmodel.DetailViewModel

class DetailActivity : BaseActivity() {

    lateinit var binding : ActivityDetailBinding
    lateinit var vmDetail : DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        vmDetail = DetailViewModel(DetailDataModel(),this)
        binding.vmDetail = vmDetail
        binding.lifecycleOwner = this
        vmDetail.initItemInformation(intent.getStringExtra(AppConstance.ITEM_NODE))
    }
}