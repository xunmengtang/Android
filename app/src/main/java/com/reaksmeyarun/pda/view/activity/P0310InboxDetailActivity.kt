package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import com.post.transfer.lanpost.base.BaseActivity
import com.reaksmeyarun.pda.R
import kotlinx.android.synthetic.main.activity_p0200_home.*

class P0310InboxDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p0310_inbox_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }
}
