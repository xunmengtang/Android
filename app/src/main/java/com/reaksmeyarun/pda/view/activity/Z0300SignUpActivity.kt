package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityZ0300SignUpBinding
import com.reaksmeyarun.pda.viewmodel.SignUpViewModel

class Z0300SignUpActivity : BaseActivity() {
    lateinit var binding : ActivityZ0300SignUpBinding
    lateinit var vmSignUp : SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_z0300_sign_up)
        window.statusBarColor = ContextCompat.getColor(this, R.color.baseColor)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        vmSignUp = SignUpViewModel(this)
        binding.vmSignUp = vmSignUp
        binding.lifecycleOwner = this
    }
}
