package com.reaksmeyarun.pda.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.ig.iginnovation.superapp.driver.mycustomclass.MyMutableLiveData
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.InboxAdapter
import com.reaksmeyarun.pda.datamodel.InboxDataModel
import com.reaksmeyarun.pda.model.InboxModel
import com.reaksmeyarun.pda.view.activity.P0300InboxActivity
import kotlinx.android.synthetic.main.p0310_inbox_layout.*

class InboxViewModel(var inboxDataModel : InboxDataModel, var activity : P0300InboxActivity) : ViewModel() {
    val TAG = "InboxViewModel"
    var inboxList = ArrayList<InboxModel>()
    var inboxDM = MyMutableLiveData<InboxDataModel>()
    init {
        inboxDM.setValue(inboxDataModel)
        inboxDataModel.state = InboxDataModel.INBOX_P0310
    }
    fun handleBackPress(view: View){
        activity.finish()
    }
    fun handleShowInboxMainScreen(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility = View.GONE
            inboxDataModel.state = InboxDataModel.INBOX_P0310
        },300)
    }
    fun handleShowInboxDetailScreen(view : View){
        activity.binding.progressing.visibility = View.VISIBLE
        android.os.Handler().postDelayed({
            activity.binding.progressing.visibility =  View.GONE
            inboxDataModel.state = InboxDataModel.INBOX_P0320
        },300)
    }
    fun bindingInbox(){
        for(value in 1..10){
            inboxList.add(InboxModel(""))
        }
        var inboxAdapter = InboxAdapter(activity, R.layout.item_inbox)
        activity.rvInbox.adapter = inboxAdapter
        activity.rvInbox.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false  )
        inboxAdapter.addItem(inboxList)
    }
}