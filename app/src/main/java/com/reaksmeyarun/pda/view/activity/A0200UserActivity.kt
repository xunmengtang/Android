package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityA0200UserBinding
import com.reaksmeyarun.pda.datamodel.StaffDataModel
import com.reaksmeyarun.pda.viewmodel.StaffViewModel

class A0200UserActivity : BaseActivity() {
    lateinit var binding : ActivityA0200UserBinding
    lateinit var vmStaff : StaffViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_a0200_user)
        vmStaff = StaffViewModel(this, StaffDataModel())
        binding.vmStaff = vmStaff
        binding.lifecycleOwner = this
    }
}
