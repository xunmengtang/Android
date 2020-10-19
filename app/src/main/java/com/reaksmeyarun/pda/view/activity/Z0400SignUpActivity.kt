package com.reaksmeyarun.pda.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityZ0400SignUpBinding
import com.reaksmeyarun.pda.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.activity_z0400_sign_up.*

class Z0400SignUpActivity : BaseActivity() {
    lateinit var binding : ActivityZ0400SignUpBinding
    lateinit var vmSignUp : SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_z0400_sign_up)
        window.statusBarColor = ContextCompat.getColor(this, R.color.baseColor)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        vmSignUp = SignUpViewModel(this)
        binding.lifecycleOwner = this
        back()
        goToHome()
    }
    private fun goToHome(){
        goToHomeScreen.setOnClickListener {
            startActivity(Intent(this,P0200HomeActivity::class.java))
        }
    }
    private fun back(){
        back_login.setOnClickListener {
            startActivity(Intent(this,Z0200SignInActivity::class.java))
        }
    }
}
