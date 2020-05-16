package com.reaksmeyarun.pda.adapter

import android.content.Context
import com.post.transfer.lanpost.base.adapter.BaseAdapter
import com.post.transfer.lanpost.base.adapter.BaseViewHolder
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