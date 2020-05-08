package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.post.transfer.lanpost.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.databinding.ActivityP0300InboxBinding
import com.reaksmeyarun.pda.datamodel.HomeDataModel
import com.reaksmeyarun.pda.datamodel.InboxDataModel
import com.reaksmeyarun.pda.viewmodel.InboxViewModel
import kotlinx.android.synthetic.main.activity_p0200_home.*

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
