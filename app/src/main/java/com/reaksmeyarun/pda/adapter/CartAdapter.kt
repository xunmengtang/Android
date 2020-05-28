package com.reaksmeyarun.pda.adapter

import android.content.Context
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.listener.OnClickListener
import com.reaksmeyarun.pda.listener.OnDeleteListener
import com.reaksmeyarun.pda.model.CartModel
import kotlinx.android.synthetic.main.item_cart_layout.view.*

class CartAdapter (context: Context, val layoutId : Int) : BaseAdapter<CartModel>(context, layoutId){
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.cartViewLayout.setOnClickListener {
            if(onClickListener==null)
                return@setOnClickListener
            onClickListener?.onClick(position)
        }
        holder.itemView.btnDelete.setOnClickListener {
            if(onClickListener==null)
                return@setOnClickListener
            onDeleteListener?.onDelete(position)
        }
    }
    var onClickListener : OnClickListener ?= null
    var onDeleteListener : OnDeleteListener ?= null
}