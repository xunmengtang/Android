package com.reaksmeyarun.pda.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.databinding.ActivityZ0300AboutUsBinding

class Z0300AboutUsActivity : AppCompatActivity() {
    lateinit var binding : ActivityZ0300AboutUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_z0300_about_us)
    }
}
