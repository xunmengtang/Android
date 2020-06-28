package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityI0100ItemBinding
import com.reaksmeyarun.pda.datamodel.ItemViewDataModel
import com.reaksmeyarun.pda.viewmodel.ItemViewModel

class I0100ItemActivity : BaseActivity() {

    lateinit var binding : ActivityI0100ItemBinding
    lateinit var vmItem : ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_i0100_item)
        vmItem = ItemViewModel(ItemViewDataModel(), this)
        binding.vmItem = vmItem
        binding.lifecycleOwner = this
    }
}
