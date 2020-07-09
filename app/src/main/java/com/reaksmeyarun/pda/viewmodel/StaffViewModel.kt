package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.StaffDataModel
import com.reaksmeyarun.pda.view.activity.A0200UserActivity

class StaffViewModel(var activity : A0200UserActivity, staffDataModel: StaffDataModel) : ViewModel() {
    var staffDM = MyMutableLiveData<StaffDataModel>()
    init {
        staffDM.setValue(staffDataModel)
    }
    fun handleStartActivity(view : View){
        activity.startActivity(activity, StaffInformationViewModel::class.java)
    }
}