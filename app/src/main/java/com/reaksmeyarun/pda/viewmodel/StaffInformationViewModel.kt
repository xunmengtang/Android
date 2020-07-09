package com.reaksmeyarun.pda.viewmodel

import androidx.lifecycle.ViewModel
import com.reaksmeyarun.pda.customclass.MyMutableLiveData
import com.reaksmeyarun.pda.datamodel.StaffDataModel
import com.reaksmeyarun.pda.view.activity.A0210UserInformationActivity

class StaffInformationViewModel(var activity : A0210UserInformationActivity, staffDataModel: StaffDataModel) : ViewModel() {
    var staffDM = MyMutableLiveData<StaffDataModel>()
    init {
        staffDM.setValue(staffDataModel)
    }
}