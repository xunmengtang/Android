package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.databinding.ActivityZ0200SettingBinding
import com.reaksmeyarun.pda.datamodel.SettingDataModel
import com.reaksmeyarun.pda.utils.MapDataUtils
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
//        RecyclerAdapter and SpinnerAdapter
        settingViewModel.bindingAdapter()

        settingViewModel.bindingRvZ0210()
        settingViewModel.bindingRvZ0220()
//        Firebase listener
        settingViewModel.recyclerViewZ0230FirebaseChildListener()
        settingViewModel.recyclerViewZ0240FirebaseChildListener()
    }
}
