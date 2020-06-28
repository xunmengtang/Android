package com.reaksmeyarun.pda.adapter

import android.content.Context
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.model.CategoryModel
class CategoryAdapter(context : Context, val layoutId : Int) : BaseAdapter<CategoryModel>(context, layoutId){
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

    }

    var onClickRecyclerViewListener : OnClickRecyclerViewListener?= null

    interface OnClickRecyclerViewListener {
        fun onClickListener(pos : Int, data: CategoryModel)
        fun onDeleteListener(pos : Int, data : CategoryModel)
    }
}