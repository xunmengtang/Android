package com.reaksmeyarun.pda.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.post.transfer.lanpost.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.databinding.ActivityP0200HomeBinding
import com.reaksmeyarun.pda.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_p0200_home.*

class P0200HomeActivity : BaseActivity(), OnNavigationItemSelectedListener {
    lateinit var binding : ActivityP0200HomeBinding
    lateinit var homeViewModel :HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p0200_home)
        homeViewModel = HomeViewModel(this)
        binding.vmHome =homeViewModel
        binding.lifecycleOwner = this
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        nav_view.setNavigationItemSelectedListener(this)
        setUpDrawerLayout()
        nav_view.setCheckedItem(R.id.nav_home)
        homeViewModel!!.bindingCategory()
        homeViewModel!!.bindingItem()

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
            R.id.nav_home ->{
                Toast.makeText(this, "Home",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_promotion ->{
                startActivityForResult(Intent(this, P0400PromotionActivity::class.java), AppConstance.PROMOTION)
            }
            R.id.nav_inbox ->{
                startActivityForResult(Intent(this, P0300InboxActivity::class.java), AppConstance.INBOX)
            }
            R.id.nav_setting ->{
                startActivityForResult(Intent(this, Z0200SettingActivity::class.java), AppConstance.SETTING)
            }
            R.id.nav_aboutUs ->{
                startActivityForResult(Intent(this, Z0300AboutUsActivity::class.java), AppConstance.ABOUT_US)
            }
            R.id.nav_signOut ->{
                Toast.makeText(this, "Sign out",Toast.LENGTH_SHORT).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        TODO :
        when(resultCode){
            AppConstance.RESULT_PROMOTION->{
                nav_view.setCheckedItem(R.id.nav_home)
            }
            AppConstance.RESULT_INBOX->{
                nav_view.setCheckedItem(R.id.nav_home)
            }
            AppConstance.RESULT_SETTING->{
                nav_view.setCheckedItem(R.id.nav_home)
            }
            AppConstance.RESULT_ABOUT_US->{
                nav_view.setCheckedItem(R.id.nav_home)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_create_transaction, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
