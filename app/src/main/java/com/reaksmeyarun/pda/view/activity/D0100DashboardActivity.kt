package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.post.transfer.lanpost.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.databinding.ActivityD0100DashboardBinding
import com.reaksmeyarun.pda.viewmodel.DashboardViewModel

class D0100DashboardActivity : BaseActivity() {
    lateinit var binding : ActivityD0100DashboardBinding
    lateinit var vmDashboard : DashboardViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_d0100_dashboard)
        vmDashboard = DashboardViewModel(this)
        binding.vmDashboard = vmDashboard
        binding.lifecycleOwner = this
    }
}
