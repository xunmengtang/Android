package com.reaksmeyarun.pda.adapter

import android.content.Context
import com.reaksmeyarun.pda.base.BaseAdapter
import com.reaksmeyarun.pda.base.BaseViewHolder
import com.reaksmeyarun.pda.model.ItemModel
import kotlinx.android.synthetic.main.items_item_layout.view.*

class HomeItemAdapter(context : Context, val layoutId : Int) : BaseAdapter<ItemModel>(context, layoutId){
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.etItemName.text = items[position].itemInformation?.itemName
    }
}