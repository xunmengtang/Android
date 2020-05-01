package com.reaksmeyarun.pda.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.post.transfer.lanpost.base.BaseActivity
import com.reaksmeyarun.pda.R

class Z100SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_z100_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, P100SignInActivity::class.java))
            finish()
        },3000)
    }
}
