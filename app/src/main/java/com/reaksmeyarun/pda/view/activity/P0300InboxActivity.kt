package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.databinding.ActivityP0300InboxBinding
import com.reaksmeyarun.pda.datamodel.InboxDataModel
import com.reaksmeyarun.pda.viewmodel.InboxViewModel

class P0300InboxActivity : BaseActivity() {
    lateinit var binding : ActivityP0300InboxBinding
    lateinit var vmInbox : InboxViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p0300_inbox)
        vmInbox = InboxViewModel(InboxDataModel(), this)
        binding.vmInbox = vmInbox
        binding.lifecycleOwner = this
        vmInbox.bindingInbox()
    }
}
