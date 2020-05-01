package com.reaksmeyarun.pda.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.post.transfer.lanpost.base.BaseActivity
import com.reaksmeyarun.pda.R
import kotlinx.android.synthetic.main.activity_p200_home.*

class P200HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p200_home)
        setSupportActionBar(toolbar)
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
        //drawer_layout.openDrawer(Gravity.LEFT)
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }
}
