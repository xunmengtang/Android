package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.CategoryAdapter
import com.reaksmeyarun.pda.adapter.ItemsAdapter
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.dataObject.Category
import com.reaksmeyarun.pda.databinding.ActivityP0200HomeBinding
import com.reaksmeyarun.pda.firebaseRepo.RequestCategory
import com.reaksmeyarun.pda.firebaseRepo.RequestItem
import com.reaksmeyarun.pda.listener.RVItemClickCallback
import com.reaksmeyarun.pda.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_p0200_home.*

class P0200HomeActivity : BaseActivity(),
    OnNavigationItemSelectedListener {
    lateinit var binding : ActivityP0200HomeBinding
    private lateinit var homeViewModel : HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p0200_home)
        homeViewModel = HomeViewModel(this)
        binding.vmHome = homeViewModel
        binding.lifecycleOwner = this
        window.setBackgroundDrawable(getDrawable(R.drawable.def_activity_bg))
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        nav_view.setNavigationItemSelectedListener(this)


        initCategory()
        initClothesRV()
        initShoesRV()
        initWatch()
        initPants()


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun initCategory(){
        val category = CategoryAdapter(this, R.layout.category_layout)
        binding.rvRecentlySearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecentlySearch.adapter = category
        category.addItem(Category.category)
        category.setItemClickCallBack(object : RVItemClickCallback<RequestCategory.ResponseCategory>{
            override fun onClick(item: RequestCategory.ResponseCategory, pos: Int) {
                Log.d("TAG","Hello activity++++-----")
                Toast.makeText(this@P0200HomeActivity,"Item",Toast.LENGTH_SHORT).show()
            }
        })
        homeViewModel.initCategory()
    }

    fun initClothesRV(){
        val itemsAdapter = ItemsAdapter(this, R.layout.item_layout)
        binding.rvClothes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvClothes.adapter = itemsAdapter
        itemsAdapter.setItemClickCallBack(object : RVItemClickCallback<RequestItem.ResponseItem>{
            override fun onClick(item: RequestItem.ResponseItem, pos: Int) {
                Log.d("TAG","Hello activity++++++++")
                Toast.makeText(this@P0200HomeActivity,"Item",Toast.LENGTH_SHORT).show()
            }
        })
        homeViewModel.requestClothes(itemsAdapter)
    }
    fun initShoesRV(){
        val itemsAdapter = ItemsAdapter(this, R.layout.item_layout)
        binding.rvShoes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvShoes.adapter = itemsAdapter
        itemsAdapter.setItemClickCallBack(object : RVItemClickCallback<RequestItem.ResponseItem>{
            override fun onClick(item: RequestItem.ResponseItem, pos: Int) {
                Log.d("TAG","Hello activity000000")
                Toast.makeText(this@P0200HomeActivity,"Item",Toast.LENGTH_SHORT).show()
            }
        })
        homeViewModel.requestShoes(itemsAdapter)
    }
    fun initWatch(){
        val itemAdater = ItemsAdapter(this,R.layout.item_layout)
        binding.rvWatch.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvWatch.adapter = itemAdater
        itemAdater.setItemClickCallBack(object :RVItemClickCallback<RequestItem.ResponseItem>{
            override fun onClick(item: RequestItem.ResponseItem, pos: Int) {
                Log.d("TAG","Hello activity_________")
                Toast.makeText(this@P0200HomeActivity,"Item",Toast.LENGTH_SHORT).show()
            }

        })
        homeViewModel.requestWatch(itemAdater)
    }
    fun initPants(){
        val itemAdater = ItemsAdapter(this,R.layout.item_layout)
        binding.rvPants.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvPants.adapter = itemAdater
        itemAdater.setItemClickCallBack(object :RVItemClickCallback<RequestItem.ResponseItem>{
            override fun onClick(item: RequestItem.ResponseItem, pos: Int) {
                Log.d("TAG","Hello activity=========")
                Toast.makeText(this@P0200HomeActivity,"Item",Toast.LENGTH_SHORT).show()
            }

        })
        homeViewModel.requestPants(itemAdater)
    }
}
