package com.reaksmeyarun.pda.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.connection.FirebaseConnection
import com.reaksmeyarun.pda.databinding.ActivityP0200HomeBinding
import com.reaksmeyarun.pda.datamodel.HomeDataModel
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.viewmodel.HomeViewModel

class P0200HomeActivity : BaseActivity(),
    OnNavigationItemSelectedListener {

    lateinit var binding : ActivityP0200HomeBinding
    lateinit var homeViewModel : HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p0200_home)
        homeViewModel = HomeViewModel(HomeDataModel(),this)
        binding.vmHome = homeViewModel
        binding.lifecycleOwner = this

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        homeViewModel.handleBubbleTabBar()

        Log.i("HomeActivity", "${FirebaseConnection.firebaseAuth.currentUser}")
    }

    override fun onBackPressed() {
        PopupMsg.alert(this, getString(R.string.msg_close),
            object : PopupMsg.OnClickButtonYesNoCallBack {
                override fun onYesCallBack() {
                    startActivity(Intent(applicationContext, P0100SignInActivity::class.java))
                    finish()
                }

                override fun onNoCallBack() {
//                    Do nothing
                }
            })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}
