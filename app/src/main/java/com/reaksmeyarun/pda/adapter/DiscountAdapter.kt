package com.reaksmeyarun.pda.adapter

import android.content.Context
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.listener.OnClickListener
import com.reaksmeyarun.pda.model.DiscountModel
import kotlinx.android.synthetic.main.item_discount_layout.view.*

class DiscountAdapter(context: Context, val layoutId : Int) : BaseAdapter<DiscountModel>(context, layoutId) {
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.discountViewLayout.setOnClickListener {
            if(onClickListener==null)
                return@setOnClickListener
            onClickListener?.onClick(position)
        }
    }
    var onClickListener : OnClickListener? = null
}