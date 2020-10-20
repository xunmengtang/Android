package com.reaksmeyarun.pda.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.ItemsAdapter
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.constance.AppConstance
import com.reaksmeyarun.pda.databinding.ActivitySeeMoreBinding
import com.reaksmeyarun.pda.firebaseRepo.RequestItem
import com.reaksmeyarun.pda.listener.RVItemClickCallback
import com.reaksmeyarun.pda.utils.ConvertUtils
import com.reaksmeyarun.pda.viewmodel.SeeMoreViewModel

class SeeMoreActivity : BaseActivity() {

    lateinit var binding : ActivitySeeMoreBinding
    lateinit var vmSeeMore : SeeMoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_see_more)
        vmSeeMore = SeeMoreViewModel(this)
        binding.vmSeeMore = vmSeeMore
        binding.lifecycleOwner = this
        initItemAdapter()
    }

    fun initItemAdapter(){
        val itemAdaper = ItemsAdapter(this, R.layout.item_layout)
        binding.rvFeature.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvFeature.adapter = itemAdaper
        itemAdaper.setItemClickCallBack(object : RVItemClickCallback<RequestItem.ResponseItem>{
            override fun onClick(item: RequestItem.ResponseItem, pos: Int) {
                val intent = Intent(this@SeeMoreActivity, DetailActivity::class.java)
                intent.putExtra(AppConstance.ITEM_NODE, ConvertUtils.init().objectToJson(item).toString())
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        })
        vmSeeMore.requestItems(intent.getStringExtra(AppConstance.SEE_MORE), itemAdaper)
    }
}