package com.reaksmeyarun.pda.viewmodel

import androidx.lifecycle.ViewModel
import com.reaksmeyarun.pda.constance.KeyConstance
import com.reaksmeyarun.pda.model.CategoryModel
import com.reaksmeyarun.pda.view.activity.C0200CategoryInformationActivity

class CategoryInformationViewModel(val activity : C0200CategoryInformationActivity) : ViewModel() {
    init {

    }

    fun getIntent() : CategoryModel = activity.intent.getParcelableExtra(KeyConstance.EDIT_CATEGORY)!!
}