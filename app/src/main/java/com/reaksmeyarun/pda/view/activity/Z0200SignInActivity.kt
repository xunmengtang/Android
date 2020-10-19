package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityZ0200SignInBinding
import com.reaksmeyarun.pda.viewmodel.SignInViewModel

class Z0200SignInActivity : BaseActivity() {
    lateinit var binding : ActivityZ0200SignInBinding
    lateinit var vmSignIn : SignInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_z0200_sign_in)
        window.statusBarColor = ContextCompat.getColor(this, R.color.baseColor)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        vmSignIn = SignInViewModel(this)
        binding.lifecycleOwner = this
    }
}
