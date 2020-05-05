package com.reaksmeyarun.pda.view.activity

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.databinding.ActivityZ0200SettingBinding

class Z0200SettingActivity : AppCompatActivity() {
    lateinit var binding : ActivityZ0200SettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_z0200_setting)
    }
}
