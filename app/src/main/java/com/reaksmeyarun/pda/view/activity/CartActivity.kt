package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.CartAdapter
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.databinding.ActivityCartBinding
import com.reaksmeyarun.pda.firebaseRepo.RequestCart
import com.reaksmeyarun.pda.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.activity_cart.*

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
        onCheckout()
    }
    private fun onCheckout(){
        checkout.setOnClickListener {
            Toast.makeText(this,"Will be coming soon...",Toast.LENGTH_SHORT).show()
        }
    }
    fun initRVCart(){
        binding.rvCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvCart.adapter = cartAdapter
        cartAdapter.setAddRemoveCallBack(object : CartAdapter.AddRemoveCallBack{
            override fun add(item: RequestCart.ResponseCart) {
                calculateSubTotal(cartAdapter.arrayListOfTotal)
            }

            override fun remove(item: RequestCart.ResponseCart) {
                calculateSubTotal(cartAdapter.arrayListOfTotal)
            }
        })
        cartAdapter.setRemoveItemCallBack(object : CartAdapter.RemoveItem {
            override fun onDelete(item: RequestCart.ResponseCart, position: Int) {
                vmCart.requestRemoveItemFromCart(cartAdapter, position)
            }
        })
        vmCart.requestCart(cartAdapter)
    }
    fun calculateSubTotal(totalList : ArrayList<Double>){
        var total : Double ?= 0.0
        for(i in totalList){
            total = total?.plus(i)
        }
        binding.totalPrice.text = "$ $total"
    }
}