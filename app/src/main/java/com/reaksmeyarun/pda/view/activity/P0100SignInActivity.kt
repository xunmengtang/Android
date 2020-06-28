package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityP0100SignInBinding
import com.reaksmeyarun.pda.datamodel.SignInDataModel
import com.reaksmeyarun.pda.datamodel.SignInDataModel.Companion.SIGN_IN_0100_CONTENT_PASSWORD
import com.reaksmeyarun.pda.utils.PopupMsg
import com.reaksmeyarun.pda.viewmodel.SignInViewModel

class P0100SignInActivity : BaseActivity() {
    lateinit var binding : ActivityP0100SignInBinding
    lateinit var vmSignIn : SignInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p0100_sign_in)
        vmSignIn = SignInViewModel(SignInDataModel(), this)
        binding.vmSignIn = vmSignIn
        binding.lifecycleOwner = this

//        setSupportActionBar(binding.toolbar)
    }

    override fun onBackPressed() {
        if(binding.vmSignIn?.signInDataModel?.state == SIGN_IN_0100_CONTENT_PASSWORD)
            binding.vmSignIn?.showP0100ContentEmail()
        else {
            PopupMsg.alert(this, getString(R.string.close),
                object : PopupMsg.OnClickButtonYesNoCallBack {
                    override fun onYesCallBack() {
                        finish()
                    }
                    override fun onNoCallBack() {}
                }
            )
        }
    }
}
