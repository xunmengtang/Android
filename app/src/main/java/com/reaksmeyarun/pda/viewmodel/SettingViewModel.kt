package com.reaksmeyarun.pda.viewmodel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.ig.iginnovation.superapp.driver.mycustomclass.MyMutableLiveData
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.SettingAdapter
import com.reaksmeyarun.pda.datamodel.SettingDataModel
import com.reaksmeyarun.pda.listener.OnClickListener
import com.reaksmeyarun.pda.model.SettingModel
import com.reaksmeyarun.pda.view.activity.Z0200SettingActivity
import kotlinx.android.synthetic.main.z0210_setting_layout.*
import kotlinx.android.synthetic.main.z0220_category_item_layout.*

class SettingViewModel(var settingDataModel: SettingDataModel, var activity : Z0200SettingActivity) : ViewModel() {
    private val TAG = "SettingViewModel"
    var settingDM = MyMutableLiveData<SettingDataModel>()
    init {
        settingDM.setValue(settingDataModel)
        settingDataModel.state = SettingDataModel.SETTING_Z0210
    }
    fun handlePressBack(view : View){
        activity.finish()
    }

    fun handleShowZ0210(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            settingDataModel.state = SettingDataModel.SETTING_Z0210
        },300)
    }
    fun handleShowZ0220(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            settingDataModel.state = SettingDataModel.SETTING_Z0220
        },300)
    }
    fun handleShowZ0230(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            settingDataModel.state = SettingDataModel.SETTING_Z0220
        },300)
    }
    fun handleShowZ0231(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            settingDataModel.state = SettingDataModel.SETTING_Z0231
        },300)
    }
    fun handleShowZ0240(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            settingDataModel.state = SettingDataModel.SETTING_Z0240
        },300)
    }
    fun handleShowZ0241(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            settingDataModel.state = SettingDataModel.SETTING_Z0241
        },300)
    }
    var settingList = ArrayList<SettingModel>()
    fun bindingRvZ0220(){
        settingList.add(SettingModel("",activity.getString(R.string.categoryLabel),0))
        settingList.add(SettingModel("",activity.getString(R.string.itemLabel),0))
        var settingAdapter = SettingAdapter(activity, R.layout.item_setting_layout)
        activity.rvZ0220Setting.adapter = settingAdapter
        activity.rvZ0220Setting.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        settingAdapter.addItem(settingList)
        settingAdapter.onClickListener = object : OnClickListener{
            override fun onClick(position: Int) {
                activity.binding.progressing.visibility = View.VISIBLE
                android.os.Handler().postDelayed({
                    activity.binding.progressing.visibility = View.GONE
                    if(position==0)
                        settingDataModel.state = SettingDataModel.SETTING_Z0230
                    else if (position == 1)
                        settingDataModel.state = SettingDataModel.SETTING_Z0240
                },300)
            }

        }
    }
    var categoryItemList = ArrayList<SettingModel>()
    fun bindingRvZ0210(){
        categoryItemList.add(SettingModel("","Language",R.drawable.ic_language))
        categoryItemList.add(SettingModel("","Category & Item",R.drawable.ic_stock))
        var categoryItemAdapter = SettingAdapter(activity, R.layout.item_setting_layout)
        activity.rvZ0210setting.adapter = categoryItemAdapter
        activity.rvZ0210setting.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        categoryItemAdapter.addItem(categoryItemList)
        categoryItemAdapter.onClickListener = object : OnClickListener{
            override fun onClick(position: Int) {
                activity.binding.progressing.visibility = View.VISIBLE
                android.os.Handler().postDelayed({
                    activity.binding.progressing.visibility = View.GONE
                    if(position == 0)
                        Toast.makeText(activity,"Language",Toast.LENGTH_SHORT).show()
                    else if (position == 1)
                        settingDataModel.state = SettingDataModel.SETTING_Z0220
                },300)
            }
        }
    }
}