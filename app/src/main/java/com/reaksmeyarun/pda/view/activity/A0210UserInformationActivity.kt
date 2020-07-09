package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityA0210UserInformationBinding
import com.reaksmeyarun.pda.datamodel.StaffDataModel
import com.reaksmeyarun.pda.viewmodel.StaffInformationViewModel

class A0210UserInformationActivity : BaseActivity() {
    lateinit var binding : ActivityA0210UserInformationBinding
    lateinit var vmStaffInformation : StaffInformationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_a0210_user_information)
        vmStaffInformation = StaffInformationViewModel(this, StaffDataModel())
        binding.vmStaffInformation = vmStaffInformation
        binding.lifecycleOwner = this
    }
}
