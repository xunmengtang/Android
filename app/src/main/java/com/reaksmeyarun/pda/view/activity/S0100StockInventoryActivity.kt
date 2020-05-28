package com.reaksmeyarun.pda.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.reaksmeyarun.pda.base.BaseActivity
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.databinding.ActivityS0100StockInventoryBinding
import com.reaksmeyarun.pda.datamodel.StockInventoryDataModel
import com.reaksmeyarun.pda.viewmodel.StockInventoryViewModel

class S0100StockInventoryActivity : BaseActivity() {
    lateinit var binding : ActivityS0100StockInventoryBinding
    lateinit var vmStock : StockInventoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_s0100_stock_inventory)
        vmStock = StockInventoryViewModel(StockInventoryDataModel(), this )
        binding.vmStock = vmStock
        binding.lifecycleOwner = this

        vmStock.bindingSpinnerS0120()
        vmStock.bindingItemS0120()
//        vmStock.bindingCategoryS0220()
    }
}
