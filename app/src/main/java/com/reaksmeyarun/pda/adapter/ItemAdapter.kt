package com.reaksmeyarun.pda.adapter

import android.content.Context
import com.post.transfer.lanpost.base.adapter.BaseAdapter
import com.post.transfer.lanpost.base.adapter.BaseViewHolder
import com.reaksmeyarun.pda.listener.OnClickListener
import com.reaksmeyarun.pda.model.ItemModel
import kotlinx.android.synthetic.main.item_item_layout.view.*

class ItemAdapter(context : Context, var layoutId : Int) : BaseAdapter<ItemModel>(context, layoutId) {
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.itemViewLayout.setOnClickListener {
            if(onClickListener== null)
                return@setOnClickListener
            onClickListener?.onClick(position)
        }
    }
    var onClickListener : OnClickListener?= null
}