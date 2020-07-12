package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityC0100CategoryBinding
import com.reaksmeyarun.pda.viewmodel.CategoryViewModel

class C0100CategoryActivity : BaseActivity() {
    lateinit var binding  : ActivityC0100CategoryBinding
    lateinit var vmCategory : CategoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_c0100_category)
        vmCategory = CategoryViewModel(this)
        binding.vmCategory = vmCategory
        binding.lifecycleOwner = this
        setActTitle(resources.getString(R.string.categoryLabel))
        vmCategory.bindingRcyCategory()
    }
}
