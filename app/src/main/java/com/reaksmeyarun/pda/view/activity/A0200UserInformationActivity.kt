package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityA0200UserInformationBinding

class A0200UserInformationActivity : BaseActivity() {
    lateinit var binding : ActivityA0200UserInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_a0200_user_information)
        binding.lifecycleOwner = this
        setActTitle(getString(R.string.information))
    }
}
