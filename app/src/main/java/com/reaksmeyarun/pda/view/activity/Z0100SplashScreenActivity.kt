package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.databinding.ActivityZ0100SplashScreenBinding
import com.reaksmeyarun.pda.viewmodel.SplashScreenViewModel

class Z0100SplashScreenActivity : BaseActivity() {
    lateinit var binding : ActivityZ0100SplashScreenBinding
    lateinit var splashScreenViewModel: SplashScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_z0100_splash_screen)
        splashScreenViewModel = SplashScreenViewModel(this)
        binding.vmSplashScreen = splashScreenViewModel
        binding.lifecycleOwner = this
    }
}
