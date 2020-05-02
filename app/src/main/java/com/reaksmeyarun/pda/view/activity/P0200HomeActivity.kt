package com.reaksmeyarun.pda.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.post.transfer.lanpost.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.databinding.ActivityP0200HomeBinding
import kotlinx.android.synthetic.main.activity_p0200_home.*

class P0200HomeActivity : BaseActivity(), OnNavigationItemSelectedListener {
    lateinit var binding : ActivityP0200HomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p0200_home)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.title = ""
        setUpDrawerLayout()
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
                Toast.makeText(this, "Promotion",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_inbox ->{
                startActivity(Intent(this, P0300InboxActivity::class.java))
            }
            R.id.nav_setting ->{
                Toast.makeText(this, "Setting",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_aboutUs ->{
                Toast.makeText(this, "About us",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_signOut ->{
                Toast.makeText(this, "Sign out",Toast.LENGTH_SHORT).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
