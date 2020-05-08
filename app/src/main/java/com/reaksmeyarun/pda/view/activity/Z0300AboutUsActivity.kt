package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.post.transfer.lanpost.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.databinding.ActivityZ0300AboutUsBinding
import kotlinx.android.synthetic.main.activity_p0200_home.*

class Z0300AboutUsActivity : BaseActivity() {
    lateinit var binding : ActivityZ0300AboutUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_z0300_about_us)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_basecolor)
    }
}