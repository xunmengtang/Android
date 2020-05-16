package com.reaksmeyarun.pda.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.post.transfer.lanpost.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.databinding.ActivityP0200HomeBinding
import com.reaksmeyarun.pda.datamodel.HomeDataModel
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_p0200_home.*

class P0200HomeActivity : BaseActivity(), OnNavigationItemSelectedListener {
    lateinit var binding : ActivityP0200HomeBinding
    lateinit var homeViewModel :HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p0200_home)
        homeViewModel = HomeViewModel(HomeDataModel(),this)
        binding.vmHome = homeViewModel
        binding.lifecycleOwner = this
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        nav_view.setNavigationItemSelectedListener(this)
        setUpDrawerLayout()
        nav_view.setCheckedItem(R.id.nav_service)
//        homeViewModel.bindingCategoryP0210()
        homeViewModel.bindingSpinnerS0210()
//        homeViewModel.bindingItemP0210()
        homeViewModel.bindingItemP0230()
        homeViewModel.bindingCartP0240()
    }
    private fun setUpDrawerLayout() {
        nav_view.itemIconTintList = null //use for set icon to default color
        val actionBarDrawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.openDrawer, R.string.closeDrawer
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }
        }
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_service ->{
                //DO NOTHING
            }
//            R.id.nav_dashboard ->{
//                startActivity(Intent(this, D0100DashboardActivity::class.java))
//            }
//            R.id.nav_stock ->{
//                startActivity(Intent(this, S0100StockInventoryActivity::class.java))
//            }
//            R.id.nav_promotion ->{
//                startActivityForResult(Intent(this, P0400PromotionActivity::class.java), AppConstance.P0400PROMOTION)
//            }
//            R.id.nav_inbox ->{
//                startActivityForResult(Intent(this, P0300InboxActivity::class.java), AppConstance.P0300INBOX)
//            }
            R.id.nav_setting ->{
                startActivityForResult(Intent(this, Z0200SettingActivity::class.java), AppConstance.Z0200SETTING)
            }
            R.id.nav_aboutUs ->{
                startActivityForResult(Intent(this, Z0300AboutUsActivity::class.java), AppConstance.Z0300ABOUT_US)
            }
            R.id.nav_signOut ->{
                PopupMsg.alert(this,getString(R.string.msg_SignOut), object : PopupMsg.OnClickButtonYesNoCallBack{
                    override fun onYesCallBack() {
                        startActivity(Intent(applicationContext, P0100SignInActivity::class.java))
                        finish()
                    }
                    override fun onNoCallBack() {
//                        DO NOTHING
                    }
                })
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            AppConstance.RESULT_P0300INBOX->{
                nav_view.setCheckedItem(R.id.nav_service)
            }
            AppConstance.RESULT_P0400PROMOTION->{
                nav_view.setCheckedItem(R.id.nav_service)
            }
            AppConstance.RESULT_Z0200SETTING->{
                nav_view.setCheckedItem(R.id.nav_service)
            }
            AppConstance.RESULT_Z0300ABOUT_US->{
                nav_view.setCheckedItem(R.id.nav_service)
            }
        }
    }
}
