package com.reaksmeyarun.pda.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.reaksmeyarun.pda.R
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.customView.MyTextView
import com.reaksmeyarun.pda.model.CategoryModel
import kotlinx.android.synthetic.main.category_item_layout.view.*

class HomeCategoryAdapter(context : Context, val layoutId : Int) : BaseAdapter<CategoryModel>(context, layoutId){
    private var viewDotSelected: View? = null
    private var viewCategoryNameSelected: MyTextView? = null
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.categoryName.text = items[position].categoryInformation?.categoryName
        holder.itemView.setOnClickListener {
            selectedItem(holder.itemView)
            itemClickCallback?.onClick(items[position], position)
        }
    }
    fun selectedItem(item: View?){
        viewDotSelected?.visibility = View.GONE
        viewCategoryNameSelected?.setTextColor(ContextCompat.getColor(context, R.color.hintTextColor))
        viewDotSelected = item?.dot
        viewCategoryNameSelected = item?.categoryName
        item?.dot?.visibility = View.VISIBLE
        item?.categoryName?.setTextColor(ContextCompat.getColor(context, R.color.black))
    }
}