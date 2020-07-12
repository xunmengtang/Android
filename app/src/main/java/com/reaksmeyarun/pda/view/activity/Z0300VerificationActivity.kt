package com.reaksmeyarun.pda.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityZ0300VerificationBinding
import com.reaksmeyarun.pda.viewmodel.VerificationViewModel

class Z0300VerificationActivity : BaseActivity() {
    private lateinit var binding : ActivityZ0300VerificationBinding
    private lateinit var vmVerify : VerificationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_z0300_verification)
        vmVerify = VerificationViewModel(this)
        binding.vmVerification = vmVerify
        binding.lifecycleOwner = this
    }
}