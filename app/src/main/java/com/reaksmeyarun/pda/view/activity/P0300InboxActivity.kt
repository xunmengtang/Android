package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.post.transfer.lanpost.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.databinding.ActivityP0300InboxBinding
import kotlinx.android.synthetic.main.activity_p0200_home.*

class P0300InboxActivity : BaseActivity() {
    lateinit var binding : ActivityP0300InboxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p0300_inbox)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_basecolor)
    }

    override fun onNavigateUp(): Boolean {
        setResult(AppConstance.RESULT_INBOX)
        finish()
        return super.onNavigateUp()
    }
}
