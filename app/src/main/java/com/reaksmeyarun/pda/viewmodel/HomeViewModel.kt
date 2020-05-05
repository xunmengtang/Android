package com.reaksmeyarun.pda.viewmodel

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.adapter.CategoryAdapter
import com.reaksmeyarun.pda.adapter.ItemAdapter
import com.reaksmeyarun.pda.model.CategoryModel
import com.reaksmeyarun.pda.model.ItemModel
import com.reaksmeyarun.pda.view.activity.P0200HomeActivity
import kotlinx.android.synthetic.main.p0210_home_main_layout.*

class HomeViewModel(var activity : P0200HomeActivity) : ViewModel() {
    val TAG = "HomeViewModel"
    var categoryList = ArrayList<CategoryModel>()
    fun bindingCategory(){
        for(value in 1..5){
            categoryList.add(CategoryModel(""))
        }
        var categoryAdapter = CategoryAdapter(activity, R.layout.item_category_layout)
        activity.rvCategory.adapter = categoryAdapter
        activity.rvCategory.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter.addItem(categoryList)
    }
    var itemList = ArrayList<ItemModel>()
    fun bindingItem(){
        for(value in 1..5){
            itemList.add(ItemModel(""))
        }
        var itemAdapter = ItemAdapter(activity, R.layout.item_item_layout)
        activity.rvItem.adapter = itemAdapter
        activity.rvItem.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        itemAdapter.addItem(itemList)
    }
}