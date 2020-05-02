package com.reaksmeyarun.pda.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.post.transfer.lanpost.base.BaseActivity
import com.reaksmeyarun.pda.R

class Z0100SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_z0100_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, P0100SignInActivity::class.java))
            finish()
        },3000)
    }
}
