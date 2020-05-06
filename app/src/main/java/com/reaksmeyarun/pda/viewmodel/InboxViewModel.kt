package com.reaksmeyarun.pda.viewmodel

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.InboxAdapter
import com.reaksmeyarun.pda.model.InboxModel
import com.reaksmeyarun.pda.view.activity.P0300InboxActivity
import kotlinx.android.synthetic.main.activity_p0300_inbox.*

class InboxViewModel(var activity : P0300InboxActivity) : ViewModel() {
    val TAG = "InboxViewModel"
    var inboxList = ArrayList<InboxModel>()
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