package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityP0200HomeBinding
import com.reaksmeyarun.pda.datamodel.HomeDataModel
import com.reaksmeyarun.pda.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_p0200_home.*

class P0200HomeActivity : BaseActivity(),
    OnNavigationItemSelectedListener {
    lateinit var binding : ActivityP0200HomeBinding
    private lateinit var homeViewModel : HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p0200_home)
        homeViewModel = HomeViewModel(HomeDataModel(),this)
        binding.vmHome = homeViewModel
        binding.lifecycleOwner = this
        window.setBackgroundDrawable(getDrawable(R.drawable.def_activity_bg))
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        homeViewModel.handleBubbleTabBar()
        homeViewModel.handleUserInformation()
        homeViewModel.initCategory()
        homeViewModel.initItem()
        nav_view.setNavigationItemSelectedListener(this)
    }
    override fun onBackPressed() = homeViewModel.handleBackPress()
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        homeViewModel.handleNavigationItemSelected(item)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
