package com.reaksmeyarun.pda.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.CartAdapter
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityCartBinding
import com.reaksmeyarun.pda.firebaseRepo.RequestCart
import com.reaksmeyarun.pda.viewmodel.CartViewModel

class CartActivity : BaseActivity() {

    lateinit var binding : ActivityCartBinding
    lateinit var vmCart : CartViewModel
    var cartLists = ArrayList<RequestCart.ResponseCart>()
    val cartAdapter = CartAdapter(this, R.layout.cart_layout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)
        vmCart = CartViewModel(this)
        binding.vmCart = vmCart
        binding.lifecycleOwner = this
        initRVCart()
    }

    fun initRVCart(){
        binding.rvCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvCart.adapter = cartAdapter
        vmCart.requestCart(cartAdapter)
    }
}