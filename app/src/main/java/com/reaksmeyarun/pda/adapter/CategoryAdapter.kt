package com.reaksmeyarun.pda.adapter

import android.content.Context
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.model.CategoryModel
import kotlinx.android.synthetic.main.layout_category_item.view.*

class CategoryAdapter(context : Context, val layoutId : Int) : BaseAdapter<CategoryModel>(context, layoutId){
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.position.text = "# ${position+1}"
        holder.itemView.title.text = items[position].categoryInformation.categoryName
        holder.itemView.size.text = items[position].categorySize.toString()
    }
}