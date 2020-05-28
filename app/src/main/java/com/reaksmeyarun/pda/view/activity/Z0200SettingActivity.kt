package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.databinding.ActivityZ0200SettingBinding
import com.reaksmeyarun.pda.datamodel.SettingDataModel
import com.reaksmeyarun.pda.viewmodel.SettingViewModel

class Z0200SettingActivity : BaseActivity() {
    lateinit var settingViewModel : SettingViewModel
    lateinit var binding : ActivityZ0200SettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_z0200_setting)
        settingViewModel = SettingViewModel(SettingDataModel(), this)
        binding.vmSetting = settingViewModel
        binding.lifecycleOwner = this
        settingViewModel.bindingRvZ0210()
        settingViewModel.bindingRvZ0220()
        settingViewModel.bindingSpinnerZ0241()
    }
}
