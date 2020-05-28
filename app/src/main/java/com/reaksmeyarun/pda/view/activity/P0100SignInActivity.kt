package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.databinding.ActivityP0100SignInBinding
import com.reaksmeyarun.pda.viewmodel.SignInViewModel

class P0100SignInActivity : BaseActivity() {
    lateinit var binding : ActivityP0100SignInBinding
    lateinit var vmSignIn :SignInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p0100_sign_in)
        vmSignIn = SignInViewModel(this)
        binding.vmSignIn = vmSignIn
        binding.lifecycleOwner = this
    }
}
