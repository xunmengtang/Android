package com.reaksmeyarun.pda.adapter

import android.content.Context
import android.widget.Toast
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.firebaseRepo.RequestCategory
import kotlinx.android.synthetic.main.category_layout.view.*

class CategoryAdapter(context : Context, val layoutId : Int) : BaseAdapter<RequestCategory.ResponseCategory>(context, layoutId){
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.categoryName.text = items[position].categoryName
        holder.itemView.setOnClickListener{
            itemClickCallback?.onClick(items[position], position)
        }
    }
}