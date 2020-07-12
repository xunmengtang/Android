package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityC0200CategoryInformationBinding

class C0200CategoryInformationActivity : BaseActivity() {
    lateinit var binding : ActivityC0200CategoryInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_c0200_category_information)
        binding.lifecycleOwner = this
        setActTitle(resources.getString(R.string.categoryInformation))
    }
}